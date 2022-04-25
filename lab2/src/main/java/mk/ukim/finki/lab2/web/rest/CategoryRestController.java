package mk.ukim.finki.lab2.web.rest;

import mk.ukim.finki.lab2.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/categories")
@RestController
@CrossOrigin
public class CategoryRestController {

    @GetMapping
    public List<Category> getCategories(){
        return Arrays.asList(Category.values());
    }

}
