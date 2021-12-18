package com.example.ledgerco.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.ledgerco.testUtility.TestFileCreator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CustomFileReaderTest {

    private static final String readableFilePath = "inputTestFile.txt";

    @BeforeAll
    static void setup() {
        TestFileCreator.create(readableFilePath, testFileContent);
    }

    @AfterAll
    static void tearDown() {
        TestFileCreator.delete(readableFilePath);
    }

    @Test
    void shouldReadLinesSuccessfully() {
        List<String> expectedText = Arrays.asList(
                "LOAN IDIDI Dale 5000 1 6",
                "LOAN MBI Harry 10000 3 7",
                "LOAN UON Shelly 15000 2 9"
        );

        List<String> readLines = CustomFileReader.readLines(readableFilePath);
        Assertions.assertLinesMatch(expectedText, readLines);
    }

    @Test
    void shouldReturnEmptyCollectionIfFileNotFound() {
        List<String> expectedText = Collections.emptyList();
        List<String> readLines = CustomFileReader.readLines("dummyFile");
        Assertions.assertLinesMatch(expectedText, readLines);
    }

    private static final String testFileContent =
            "LOAN IDIDI Dale 5000 1 6\n" +
            "LOAN MBI Harry 10000 3 7\n" +
            "LOAN UON Shelly 15000 2 9";
}