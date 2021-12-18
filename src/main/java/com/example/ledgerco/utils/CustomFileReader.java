package com.example.ledgerco.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {

    private CustomFileReader(){}

    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        try (FileReader fileReader = new FileReader(file); BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
               lines.add(line);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return lines;
    }
}
