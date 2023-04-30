package com.example.naumentest.model;

import com.example.naumentest.util.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@ComponentScan
public interface AgeDbHandler extends CrudRepository<Person, Long> {
    boolean existsByNameAndAge(String name, Integer age);
    Person findByName(String name);
    Person findFirstByOrderByAgeDesc();
}
