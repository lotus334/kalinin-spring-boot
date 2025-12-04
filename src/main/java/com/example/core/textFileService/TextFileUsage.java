package com.example.core.textFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Создай систему для работы с файлами
 * 1. Создай класс TextFile, реализующий все интерфейсы
 * 2. Добавь логирование операций
 * 3. Реализуй проверку состояния файла перед операциями
 */
public class TextFileUsage {

    public static void main(String[] args) {
        System.out.println("1. Using Files: " + checkUsingFiles()/1000000 + "ms");

        System.out.println("2. Using Files buffered: " + checkUsingFilesBufferedWithResources()/1000000 + "ms");

        System.out.println("3. Using Files buffered without resources: " + checkUsingFilesBufferedWithoutResources()/1000000 + "ms");

        System.out.println("4. Using Files auto closeable: " + checkUsingFilesAutoCloseable()/1000000 + "ms");

        System.out.println("4. Using Files auto closeable: " + checkUsingFilesAutoCloseable()/1000000 + "ms");

        System.out.println("3. Using Files buffered without resources: " + checkUsingFilesBufferedWithoutResources()/1000000 + "ms");

        System.out.println("2. Using Files buffered: " + checkUsingFilesBufferedWithResources()/1000000 + "ms");

        System.out.println("1. Using Files: " + checkUsingFiles()/1000000 + "ms");
    }

    private static long checkUsingFiles() {
        long start = System.nanoTime();

        String fileName = "TextFile.txt";

        NIOFilesOnly file = new NIOFilesOnly(fileName);

        String readBefore = file.read();
//        System.out.println(readBefore);

        file.write("Beginning of the file\n");

        file.write("End of the file");

        String readFinal = file.read();
//        System.out.println(readFinal);

        file.close();

        try {
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return System.nanoTime() - start;
    }

    private static long checkUsingFilesBufferedWithResources() {
        long start = System.nanoTime();

        String fileName = "TextFile.txt";

        FilesBufferedWithResources file = new FilesBufferedWithResources(fileName);

        String readBefore = file.read();
//        System.out.println(readBefore);

        file.write("Beginning of the file\n");

        file.write("End of the file");

        String readFinal = file.read();
//        System.out.println(readFinal);

        file.close();

        try {
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return System.nanoTime() - start;
    }

    private static long checkUsingFilesBufferedWithoutResources() {
        long start = System.nanoTime();

        String fileName = "TextFile.txt";

        FilesBufferedWithoutResources file = new FilesBufferedWithoutResources(fileName);

        String readBefore = file.read();
//        System.out.println(readBefore);

        file.write("Beginning of the file\n");

        file.write("End of the file");

        String readFinal = file.read();
//        System.out.println(readFinal);

        file.close();

        try {
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return System.nanoTime() - start;
    }

    private static long checkUsingFilesAutoCloseable() {
        long start = System.nanoTime();

        String fileName = "TextFile.txt";

        try (IOFilesAutoCloseable file = new IOFilesAutoCloseable(fileName)) {
            String readBefore = file.read();
//            System.out.println(readBefore);

            file.write("Beginning of the file\n");

            file.write("End of the file");

            String readFinal = file.read();
//            System.out.println(readFinal);

            file.close();

            try {
                Files.deleteIfExists(Path.of(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return System.nanoTime() - start;
    }
}
