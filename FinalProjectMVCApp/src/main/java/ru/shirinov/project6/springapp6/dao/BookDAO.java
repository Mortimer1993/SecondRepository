package ru.shirinov.project6.springapp6.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shirinov.project6.springapp6.models.Book;
import ru.shirinov.project6.springapp6.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> index(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public Optional<Book> show(String name, String author, int year) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=? AND author=? AND year=?", new Object[]{name, author, year},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public int currentReader(int bookId) {
        return jdbcTemplate.queryForObject("SELECT person_id FROM Book WHERE book_id=?",
                new Object[]{bookId}, Integer.class);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void edit(Book updateBook, int id) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?",
                updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), id);
    }


    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void issue(Person pickUpPerson, int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", pickUpPerson.getPersonId(), bookId);
    }

    public void release(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", null, bookId);
    }


}
