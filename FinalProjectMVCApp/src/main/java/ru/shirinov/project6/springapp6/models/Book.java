package ru.shirinov.project6.springapp6.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "name")
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 100, message = "Название книги не может быть короче 2 и длиннее 100 символов")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Поле автор не может быть пустым. Если автор не известен - внесите \"Неизвестный автор\"")
    @Size(min = 2, max = 100, message = "Поле автор не может быть короче 2 и длиннее 100 символов")
    private String author;

    @Column(name = "year")
    @Min(value = 1000, message = "Год не может быть менее 1000")
    @Max(value = 2023, message = "Год не может не быть более 2023")
    private int year;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;


    public Book() {

    }

    public Book(String name, String author, int year, int bookId) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.bookId = bookId;
    }

    public boolean getIsExpired() {
        Date currentDate = new Date();

        if (currentDate.getTime() - takenAt.getTime() > 864_000_000) {
            isExpired = true;
        }

        return isExpired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

}
