package com.example.core.io.textFileService;

import java.io.*;

public class IOFilesAutoCloseable implements Readable, Writable, Closeable, AutoCloseable {

    private final File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    public IOFilesAutoCloseable(String filePath) {
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String read() {
        if (!file.exists()) {
            return "NO SUCH FILE";
        }

        if (reader == null) {
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        StringBuilder content = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    @Override
    public void write(String data) {
        if (!file.exists()) {
            return;
        }

        if (writer == null) {
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
