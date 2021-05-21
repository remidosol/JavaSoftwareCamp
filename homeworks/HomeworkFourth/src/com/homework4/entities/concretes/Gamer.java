package com.homework4.entities.concretes;

import com.homework4.entities.abstracts.Entity;

import java.util.Date;

public class Gamer extends User implements Entity {

    private int id;
    private int userId;
    private String firstName;
    private String lastName;
    private String nationalId;
    private int yearOfBirth;

    public Gamer(int id, String email, String password,
                 String firstName, String lastName,
                 String nationalId, int yearOfBirth) {
        super(id, email, password);
        this.id = id;
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
