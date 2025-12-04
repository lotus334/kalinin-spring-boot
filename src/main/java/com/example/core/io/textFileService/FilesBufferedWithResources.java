package com.example.core.io.textFileService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesBufferedWithResources implements Readable, Writable, Closeable {

    private final Path path;

    public FilesBufferedWithResources(String fileName) {
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
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND) ) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}
