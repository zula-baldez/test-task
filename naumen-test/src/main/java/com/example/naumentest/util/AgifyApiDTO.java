package com.example.naumentest.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AgifyApiDTO implements Serializable {
    Integer age;
    Integer count;
    String name;
}
