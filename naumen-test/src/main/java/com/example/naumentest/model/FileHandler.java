package com.example.naumentest.model;

import com.example.naumentest.util.Person;
import com.example.naumentest.util.TextFileAgeReader;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;

@Component
@NoArgsConstructor
@ComponentScan
public class FileHandler {

    @Autowired
    private TextFileAgeReader textFileAgeReader;
    @Autowired
    private AgeDbHandler ageDbHandler;
    public void readDataAndRenewDb(String path) throws FileNotFoundException, ParseException {
        HashMap<String, Integer> data = textFileAgeReader.getDataFromFile(path);
        data.forEach((key, value) -> {
            if(!ageDbHandler.existsByNameAndAge(key, value)) {
                Person person = new Person();
                person.configPerson(key, value, 0);
                ageDbHandler.save(person);
            }
        });
    }
}
