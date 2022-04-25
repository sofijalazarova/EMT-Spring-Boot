package mk.ukim.finki.lab2.model;

import lombok.Data;
import mk.ukim.finki.lab2.model.enumerations.Category;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Author author;
    private Integer availableCopies;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Book() {
    }

    public Book(String name, Author author, Integer availableCopies, Category category) {
        this.name = name;
        this.author = author;
        this.availableCopies = availableCopies;
        this.category = category;
    }
}
