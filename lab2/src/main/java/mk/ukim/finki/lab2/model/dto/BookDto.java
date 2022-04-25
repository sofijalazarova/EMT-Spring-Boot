package mk.ukim.finki.lab2.model.dto;

import lombok.Data;
import mk.ukim.finki.lab2.model.enumerations.Category;

@Data
public class BookDto {

    private String name;

    private Long author;

    private Integer availableCopies;

    private Category category;

    public BookDto() {
    }

    public BookDto(String name, Long author, Integer availableCopies, Category category) {
        this.name = name;
        this.author = author;
        this.availableCopies = availableCopies;
        this.category = category;
    }
}
