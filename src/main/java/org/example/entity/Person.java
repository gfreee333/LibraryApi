package org.example.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {
    @Size(min = 15, max = 100, message = "Fill name is not correct!")
    private String fullName;
    @Min(value = 0, message = "yearOfBirth it not correct!")
    private int yearOfBirth;
    public Person(){}
    public Person(String fullName, int yearOfBirth){
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

}
