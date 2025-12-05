package com.example.io.copy;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CopyTest {

    @Test
    void copyNotExistingFile() {
        assertThrows(CopyException.class, () -> Copy.copy("not-exist", "not-exist2"));
    }

    @Test
    void copyTheSameFile() {
        String from = "pom.xml";
        String to = "pom2.xml.iml";
        Copy.copy(from, to);
        try {
            assertArrayEquals(Files.readAllBytes(Path.of(to)), Files.readAllBytes(Path.of(from)));
        } catch (IOException e) {
            assert false;
        }
    }
}