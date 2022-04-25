package mk.ukim.finki.lab2.service.impl;
import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.repository.jpa.AuthorRepository;
import mk.ukim.finki.lab2.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public List<Author> listAuthors() {
        return this.authorRepository.findAll();
    }
}
