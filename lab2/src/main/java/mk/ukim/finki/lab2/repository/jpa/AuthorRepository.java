package mk.ukim.finki.lab2.repository.jpa;

import mk.ukim.finki.lab2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
