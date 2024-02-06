package ru.shirinov.project6.springapp6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shirinov.project6.springapp6.models.Person;
import ru.shirinov.project6.springapp6.repositories.PeopleRepositories;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public PeopleService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    public List<Person> findAll() {
        return peopleRepositories.findAll();
    }

    public Person findOne(int id) {
        return peopleRepositories.findById(id).orElse(null);
    }

    public Optional<Person> findByNameAndYearOfBirth(String name, int yearOfBirth) {
        return peopleRepositories.findByNameAndYearOfBirth(name, yearOfBirth);
    }

    @Transactional
    public void save(Person person) {
        peopleRepositories.save(person);
    }

    @Transactional
    public void update(Person updatePerson, int id) {
        updatePerson.setPersonId(id);
        peopleRepositories.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepositories.deleteById(id);
    }
}
