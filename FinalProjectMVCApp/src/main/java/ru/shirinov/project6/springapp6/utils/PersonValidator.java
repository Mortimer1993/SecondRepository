package ru.shirinov.project6.springapp6.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shirinov.project6.springapp6.models.Person;
import ru.shirinov.project6.springapp6.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findByNameAndYearOfBirth(person.getName(), person.getYearOfBirth()).isPresent()) {
            errors.rejectValue("name", "", "Человек с такими данными уже существует");
        }
    }
}
