package com.example.core.io.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Создать программу, которая копирует содержимое одного текстового файла в другой
 * Навыки: базовая работа с FileReader/FileWriter
 */
public class Copy {

    public static void copy(String from, String to) {
        Path pathFrom = Path.of(from);
        if (Files.notExists(pathFrom)) {
            throw new CopyException("File not found");
        }
        Path pathTo = Path.of(to);
        try {
            if (Files.exists(pathTo)) {
                Files.delete(pathTo);
            }
            Files.copy(pathFrom, pathTo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
