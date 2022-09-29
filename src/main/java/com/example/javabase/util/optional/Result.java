package com.example.javabase.util.optional;

import lombok.Getter;

@Getter
public class Result {
    private Address address;
}

@Getter
class Address {
    private Province province;
}

@Getter
class Province {
    private String name;
}


