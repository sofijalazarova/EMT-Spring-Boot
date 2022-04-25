package mk.ukim.finki.lab2.service;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Country;

import java.util.List;

public interface AuthorService {

    void delete(Long id);

    List<Author> listAuthors();
}

