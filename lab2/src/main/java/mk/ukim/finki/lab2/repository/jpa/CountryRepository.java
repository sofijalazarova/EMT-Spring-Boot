package mk.ukim.finki.lab2.repository.jpa;

import mk.ukim.finki.lab2.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    void deleteByName(String name);
}
