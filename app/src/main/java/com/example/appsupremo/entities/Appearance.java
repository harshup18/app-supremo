package com.example.appsupremo.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Appearance implements Serializable {
    private String gender;

    private String race;

    private List<String> height;

    private String eyeColor;

    private String hairColor;
}
