package com.example.appsupremo.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class SuperHero implements Serializable {

    private String name;

    private CharacterImage image;

    private String description;

    private Appearance appearance;

}
