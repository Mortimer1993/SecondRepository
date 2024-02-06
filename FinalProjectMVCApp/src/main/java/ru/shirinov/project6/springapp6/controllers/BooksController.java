package ru.shirinov.project6.springapp6.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shirinov.project6.springapp6.models.Book;
import ru.shirinov.project6.springapp6.models.Person;
import ru.shirinov.project6.springapp6.services.BooksService;
import ru.shirinov.project6.springapp6.services.PeopleService;
import ru.shirinov.project6.springapp6.utils.BookValidator;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "books_per_page", required = false) String booksPerPage,
            @RequestParam(value = "sort_by_year", required = false) boolean isSort,
            Model model) {

        if (page == null || booksPerPage == null) {
            model.addAttribute("books", booksService.findAll(isSort));
        } else {
            model.addAttribute("books", booksService.findAllWithPaginationAndSorting(page, booksPerPage, isSort));
        }

        return "books/index";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model bookModel, Model personModel, Model peopleModel,
                       @ModelAttribute("person") Person person) {
        bookModel.addAttribute("book", booksService.findOne(id));
        peopleModel.addAttribute("people", peopleService.findAll());

        try {
            personModel.addAttribute("isHaveReader", peopleService.findOne(booksService.findCurrentReader(id)));
        } catch (NullPointerException e) {
            personModel.addAttribute("isHaveReader", null);
        }

        return "books/show";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }


    @GetMapping("/search")
    public String searchPage() {
        return "/books/search";
    }


    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("books", booksService.findBooksByName(query));

        return "/books/search";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);
        return "redirect:/books";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(book, id);
        return "redirect:/books";
    }


    @PatchMapping("/{id}/add")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.issue(person, id);
        return "redirect:/books";
    }


    @PatchMapping("{id}/take")
    public String update(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
