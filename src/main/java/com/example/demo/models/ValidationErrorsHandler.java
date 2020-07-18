package com.example.demo.models;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorsHandler {
    public static Map<String,String> mapErrors(Errors errors){
        Map mappedErrors=new HashMap();
        for(FieldError e:errors.getFieldErrors()){
            mappedErrors.put(e.getField(), e.getDefaultMessage());
        }
        return mappedErrors;
    }
}
