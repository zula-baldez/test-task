package com.example.naumentest.util;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private Integer timesRequested;

    public void configPerson(String name, Integer age, Integer timesRequested) {
        this.name = name;
        this.age = age;
        this.timesRequested = timesRequested;

    }
}
