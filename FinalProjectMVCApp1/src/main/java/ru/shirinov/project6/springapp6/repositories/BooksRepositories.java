package ru.shirinov.project6.springapp6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shirinov.project6.springapp6.models.Book;


import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
    List<Book> findByPersonPersonId(int id);

    Optional<Book> findByNameAndAuthorAndYear(String name, String author, int year);

    List<Book> findByNameStartingWith(String startingWith);

}
