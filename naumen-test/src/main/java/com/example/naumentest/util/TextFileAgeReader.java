package com.example.naumentest.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;
@Component
@NoArgsConstructor
public class TextFileAgeReader {
    private String pathToFile = "";
    private HashMap<String, Integer> data;

    private void readAllData() throws FileNotFoundException, ParseException, NumberFormatException {
        File file = new File(pathToFile);
        Scanner input = new Scanner(file);
        HashMap<String, Integer> names = new HashMap<>();
        while (input.hasNext()) {
            String data = input.next();
            String[] splitted = data.split("_");
            if (splitted.length != 2) {
                throw new ParseException("File can not be parsed", 0);
            }
            int age;
            try {
                age = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException e) {
                throw new ParseException("File can not be parsed", 0);
            }

            names.put(splitted[0], age);

        }

        this.data = names;

    }

    public String getPathToFile() {
        return pathToFile;
    }

    public HashMap<String, Integer> getDataFromFile(String pathToFile) throws FileNotFoundException, ParseException, NumberFormatException {
        this.pathToFile = pathToFile;
        readAllData();
        return data;

    }
}
