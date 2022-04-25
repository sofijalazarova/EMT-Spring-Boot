package mk.ukim.finki.lab2.web.rest;


import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.service.AuthorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public List<Author> getAllAuthors(){
        return authorService.listAuthors();
    }
}
