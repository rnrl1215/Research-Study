package com.practicalbusiness.study.modelmapper.dto;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CustomerDto {
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
}
