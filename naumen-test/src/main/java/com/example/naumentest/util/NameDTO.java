package com.example.naumentest.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameDTO {
    private String name;

    public NameDTO(String name) {
        this.name = name;
    }
}
