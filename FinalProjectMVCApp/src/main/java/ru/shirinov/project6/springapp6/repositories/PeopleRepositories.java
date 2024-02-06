package ru.shirinov.project6.springapp6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shirinov.project6.springapp6.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepositories extends JpaRepository<Person, Integer> {
    Optional<Person> findByNameAndYearOfBirth(String name, int yearOfBirth);


}

