package com.example.ledgerco.main;

import com.example.ledgerco.Geektrust;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GeektrustTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    static void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    static void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @AfterEach
    void cleanup() {
        outContent.reset();
    }

    @Test
    void shouldRunApplicationAndReadCommandsAndExecuteTestInput1() {
        integrationTest("src/test/resources/testInput1.txt", testOutput1);
    }

    @Test
    void shouldRunApplicationAndReadCommandsAndExecuteTestInput2() {
        integrationTest("src/test/resources/testInput2.txt", testOutput2);
    }

    private void integrationTest(String inputFilePath, String expectedOutput) {
        Assertions.assertDoesNotThrow(() -> Geektrust.main(new String[]{inputFilePath}));
        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    private final static String testOutput1 =
            "IDIDI Dale 1000 55\n" +
                    "IDIDI Dale 8000 20\n" +
                    "MBI Harry 1044 12\n" +
                    "MBI Harry 0 24\n";

    private final static String testOutput2 =
            "IDIDI Dale 1326 9\n" +
                    "IDIDI Dale 3652 4\n" +
                    "UON Shelly 15856 3\n" +
                    "MBI Harry 9044 10\n";

}