package com.homework4.simulatedValidator;

public class ValidatorManager {
    public boolean validateUser(String nationalId, String fullName, int yearOfBirth) {
        if (!nationalId.isEmpty() && !fullName.isEmpty() && yearOfBirth <= 2021){
            System.out.println("Gamer creation validation has been success!");
            return true;
        } else {
            System.out.println("Gamer creation validation has been failed!");
            return false;
        }
    }
}
