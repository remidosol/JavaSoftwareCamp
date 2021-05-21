package com.homework4.core;

import com.homework4.simulatedValidator.ValidatorManager;

public class ValidatorManagerAdapter implements ValidatorService{
    @Override
    public boolean validateUser(String nationalId, String fullName, int yearOfBirth) {
        ValidatorManager validation = new ValidatorManager();
        return validation.validateUser(nationalId, fullName, yearOfBirth);
    }
}
