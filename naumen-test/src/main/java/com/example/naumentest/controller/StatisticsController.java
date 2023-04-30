package com.example.naumentest.controller;

import com.example.naumentest.model.AgeDbHandler;
import com.example.naumentest.util.AgeDTO;
import com.example.naumentest.util.NameDTO;
import com.example.naumentest.util.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatisticsController {
    @Autowired
    private AgeDbHandler ageDbHandler;
    @GetMapping("/request-statistics")
    @CrossOrigin
    public ResponseEntity<AgeDTO> timesNameWasRequested(@RequestParam String name) {
        Person person = ageDbHandler.findByName(name);
        if(person != null) {
            return new ResponseEntity<>(new AgeDTO(person.getTimesRequested()), HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(new AgeDTO(0), HttpStatusCode.valueOf(200));
        }
    }
    @GetMapping("/max-age")
    @CrossOrigin
    public ResponseEntity<NameDTO> getMaxTimesRequested() {
        Person person = ageDbHandler.findFirstByOrderByAgeDesc();
        if(person != null) {
            return new ResponseEntity<>(new NameDTO(person.getName()), HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(new NameDTO("NO DATA"), HttpStatusCode.valueOf(200));
        }
    }

}
