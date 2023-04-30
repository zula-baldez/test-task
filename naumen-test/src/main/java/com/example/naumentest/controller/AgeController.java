package com.example.naumentest.controller;

import com.example.naumentest.model.AgeDbHandler;
import com.example.naumentest.model.FileHandler;
import com.example.naumentest.network.http.request.HttpRequestToAgifyApiHandler;
import com.example.naumentest.util.AgeDTO;
import com.example.naumentest.util.AgifyApiDTO;
import com.example.naumentest.util.NameDTO;
import com.example.naumentest.util.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.FileNotFoundException;
import java.text.ParseException;

@Controller
public class AgeController {
    @Autowired
    private FileHandler fileHandler;
    @Autowired
    private AgeDbHandler ageDbHandler;
    @Autowired
    private HttpRequestToAgifyApiHandler httpRequestToAgifyApiHandler;
    private final String path;
    public AgeController(@Value("#{systemProperties['path']}") String path) {
        this.path = path;
    }
    @PostConstruct
    public void configBean() throws ParseException, FileNotFoundException {
        if(path == null) {
            throw new FileNotFoundException();
        }
        fileHandler.readDataAndRenewDb(path);

    }
    @GetMapping("/age")
    @Transactional
    @CrossOrigin
    public ResponseEntity<AgeDTO> getAgeByName(@RequestParam String name) {
        Person person = ageDbHandler.findByName(name);
        if(person != null) {

            person.setTimesRequested(person.getTimesRequested() + 1);
            return new ResponseEntity<>(new AgeDTO(person.getAge()), HttpStatusCode.valueOf(200));
        } else {
            Person newPerson = new Person();
            AgifyApiDTO agifyApiDTO= httpRequestToAgifyApiHandler.getDataAboutMissingName(name);
            if(agifyApiDTO.getAge() == null) {
                newPerson.configPerson(name, name.length(), 1);
            } else {
                newPerson.configPerson(name, agifyApiDTO.getAge(), 1);
            }
            ageDbHandler.save(newPerson);
            return new ResponseEntity<>(new AgeDTO(newPerson.getAge()), HttpStatusCode.valueOf(200));
        }

    }


}
