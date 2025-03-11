package org.example.util;

import org.example.dao.LibraryDAO;
import org.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {
    private final LibraryDAO libraryDAO;
    @Autowired
    public PersonValidator(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {
        //Реализовать условия при котором ввод будет
        //являться некоректным
    }
}
