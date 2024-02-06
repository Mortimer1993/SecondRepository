package ru.shirinov.project6.springapp6.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shirinov.project6.springapp6.models.Book;
import ru.shirinov.project6.springapp6.services.BooksService;

@Component
public class BookValidator implements Validator {
    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (booksService.findOne(book.getName(), book.getAuthor(), book.getYear()).isPresent()) {
            errors.rejectValue("name", "", "Книга с такими данными уже существует");
        }
    }
}
