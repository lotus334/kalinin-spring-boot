package com.example.core.io.textFileService;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;

public class NIOFilesOnly implements Readable, Writable, Closeable {

    private final Path path;

    public NIOFilesOnly(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("File is null");
        }

        this.path = Path.of(fileName);
    }

    @Override
    public String read() {
        try {
            return Files.exists(path) ? Files.readString(path) : "NO SUCH FILE";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String data) {
        try {
            Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}