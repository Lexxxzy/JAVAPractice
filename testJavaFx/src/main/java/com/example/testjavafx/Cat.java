package com.example.testjavafx;


import java.util.ArrayList;

public class Cat{
    public String catName;
    public String catAge;
    public String Breed;
    public String DateOfBirth;
    public String catColor;
    public ArrayList<Med> catMeds = new ArrayList<>(); //здесь хранятся все прививки

    public Cat(String name,String DateOfBirth,String age, String breed,
               String catsMedDate, String catsMedType, String catsMedTitle)
    {
        catName = name;
        catAge = age;
        this.DateOfBirth = DateOfBirth;
        Breed = breed;
        addMed(catsMedDate, catsMedType, catsMedTitle);
    }
    public void addMed(String catsMedDate, String catsMedType, String catsMedTitle)
    {
        catMeds.add(new Med(catsMedDate,catsMedType,catsMedTitle));
    }

    public String getParams()
    {
        return "Кличка: " + catName + "\tВозраст: " + catAge + " лет\tПорода: " + Breed + "\tЦвет: " +catColor;
    }

    public String getName()
    {
        return catName;
    }
}

