package ru.shirinov.project6.springapp6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shirinov.project6.springapp6.models.Book;
import ru.shirinov.project6.springapp6.models.Person;
import ru.shirinov.project6.springapp6.repositories.BooksRepositories;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepositories booksRepositories;

    @Autowired
    public BooksService(BooksRepositories booksRepositories) {
        this.booksRepositories = booksRepositories;
    }

    public List<Book> findAll(boolean isSort) {
        if (isSort) {
            return booksRepositories.findAll(Sort.by("year"));
        } else {
            return booksRepositories.findAll();
        }
    }

    public List<Book> findAllOnSelectedReader(int readerId) {
        return booksRepositories.findByPersonPersonId(readerId);
    }

    public List<Book> findAllWithPaginationAndSorting(String page, String bookPerPage, boolean isSort) {
        if (isSort) {
            return booksRepositories.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(bookPerPage),
                    Sort.by("year"))).getContent();
        } else {
            return booksRepositories.findAll(PageRequest.of(Integer.parseInt(page),
                    Integer.parseInt(bookPerPage))).getContent();
        }
    }

    public Book findOne(int id) {
        return booksRepositories.findById(id).orElse(null);
    }

    public List<Book> findBooksByName(String searchString) {
        return booksRepositories.findByNameStartingWith(searchString);
    }

    public Optional<Book> findOne(String name, String author, int year) {
        return booksRepositories.findByNameAndAuthorAndYear(name, author, year);
    }

    public int findCurrentReader(int bookId) {
        return Objects.requireNonNull(booksRepositories.findById(bookId).orElse(null)).getPerson().getPersonId();
    }

    @Transactional
    public void save(Book book) {
        booksRepositories.save(book);
    }

    @Transactional
    public void update(Book updateBook, int id) {

        updateBook.setBookId(id);
        updateBook.setPerson(findOne(id).getPerson());

        booksRepositories.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepositories.deleteById(id);
    }

    @Transactional
    public void issue(Person pickUpPerson, int bookId) {
        booksRepositories.findById(bookId).ifPresent(
                book -> {
                    book.setPerson(pickUpPerson);
                    book.setTakenAt(new Date());
                }
        );
    }

    @Transactional
    public void release(int bookId) {
        booksRepositories.findById(bookId).ifPresent(
                book -> {
                    book.setPerson(null);
                    book.setTakenAt(null);
                }
        );
    }
}
