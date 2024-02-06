package ru.shirinov.project6.springapp6.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "name")
    @Size(min=5, max=100, message = "Имя не должно быть короче 5 и больше 100 символов")
    @NotEmpty(message="Имя не должно быть пустым")
    private String name;

    @Column(name = "year_of_birth")
    @Min(value = 1940, message = "Год рождения не может быть менее 1940")
    @Max(value = 2023, message = "Год рождения не может быть больше 2023")
    private int yearOfBirth;

    @OneToMany(mappedBy = "person")
    private List<Book> books;


    public Person() {

    }

    public Person(String name, int yearOfBirth, int personId) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
