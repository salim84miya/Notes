package com.example.notesapp.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordInputValidator,String>{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        String newString = s.trim();
        Boolean isValid = true;

        if(newString.length()<8 || newString.length()>20){
            isValid = false;
        }


        return isValid;
    }
}
