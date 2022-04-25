package mk.ukim.finki.lab2.service.impl;

import mk.ukim.finki.lab2.model.Country;
import mk.ukim.finki.lab2.repository.jpa.CountryRepository;
import mk.ukim.finki.lab2.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void delete(String name) {
        this.countryRepository.deleteByName(name);
    }

    @Override
    public List<Country> listCountries() {
        return this.countryRepository.findAll();
    }
}
