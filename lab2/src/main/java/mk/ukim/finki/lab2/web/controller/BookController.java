package mk.ukim.finki.lab2.web.controller;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.Country;
import mk.ukim.finki.lab2.model.enumerations.Category;
import mk.ukim.finki.lab2.service.AuthorService;
import mk.ukim.finki.lab2.service.BookService;
import mk.ukim.finki.lab2.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final CountryService countryService;
    private final AuthorService authorService;

    public BookController(BookService bookService, CountryService countryService, AuthorService authorService) {
        this.bookService = bookService;
        this.countryService = countryService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBookPage(@RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Book> books = this.bookService.findAll();
        model.addAttribute("books",books);
        model.addAttribute("bodyContent","books");
        return "master-template";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.listAuthors();
            List<Country> countries = this.countryService.listCountries();
            model.addAttribute("authors", authors);
            model.addAttribute("countries", countries);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        List<Author> authors = this.authorService.listAuthors();
        List<Country> countries = this.countryService.listCountries();
        model.addAttribute("authors", authors);
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBook(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Integer availableCopies,
            @RequestParam Category category,
            @RequestParam Long author) {
        if (id != null) {
            this.bookService.edit(id, name, author, availableCopies, category);
        } else {
            this.bookService.save(name, author, availableCopies, category);
        }
        return "redirect:/books";
    }
}
