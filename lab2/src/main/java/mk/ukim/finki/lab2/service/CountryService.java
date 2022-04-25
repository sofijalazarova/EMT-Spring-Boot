package mk.ukim.finki.lab2.service;

import mk.ukim.finki.lab2.model.Country;

import java.util.List;

public interface CountryService {

    void delete(String name);

    List<Country> listCountries();

}

