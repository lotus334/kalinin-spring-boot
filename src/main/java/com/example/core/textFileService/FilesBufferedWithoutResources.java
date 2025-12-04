package com.example.core.textFileService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesBufferedWithoutResources implements Readable, Writable, Closeable {

    private final Path path;
    private BufferedReader reader;
    private BufferedWriter writer;

    public FilesBufferedWithoutResources(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("File is null");
        }

        this.path = Path.of(fileName);
    }

    @Override
    public String read() {
        if (!Files.exists(path)) {
            return "NO SUCH FILE";
        }
        try {
            if (reader == null) {
                reader = Files.newBufferedReader(path);
            }

            return reader.lines().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String data) {
        try {
            if (writer == null) {
                writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

            writer.write(data);
            writer.flush(); // TODO если не вызвать flush, то данные не сохранятся. Хрень
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (reader != null) {
                reader.close();
                reader = null;
            }
            if (writer != null) {
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
