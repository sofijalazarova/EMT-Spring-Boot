package mk.ukim.finki.lab2.service.impl;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.dto.BookDto;
import mk.ukim.finki.lab2.model.enumerations.Category;
import mk.ukim.finki.lab2.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.lab2.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab2.repository.jpa.AuthorRepository;
import mk.ukim.finki.lab2.repository.jpa.BookRepository;
import mk.ukim.finki.lab2.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Long authorId, Integer availableCopies, Category category) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name,author,availableCopies,category)));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {

        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(), author, bookDto.getAvailableCopies(), bookDto.getCategory())));

    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Long authorId, Integer availableCopies, Category category) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));

    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));

    }

    @Override
    public Optional<Book> markBookAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }

        return Optional.of(bookRepository.save(book));
    }
}
