package com.example.ledgerco.testUtility;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestFileCreator {

    static Logger logger = Logger.getLogger("TestFileCreator");

    public static String create( String fileName, String fileContent) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            writer.write(fileContent);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed while creating test a file", e);
        }

        return fileName;
    }

    public static void delete(String fileName) {
        new File(fileName).delete();
    }

}
