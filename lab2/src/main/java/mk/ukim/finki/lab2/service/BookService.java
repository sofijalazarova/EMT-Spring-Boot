package mk.ukim.finki.lab2.service;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.dto.BookDto;
import mk.ukim.finki.lab2.model.enumerations.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    void deleteById(Long id);

    Optional<Book> save(String name, Long author, Integer availableCopies, Category category);

    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, String name, Long author, Integer availableCopies, Category category);

    Optional<Book> edit(Long id, BookDto bookDto);

    Optional<Book> markBookAsTaken(Long id);
}
