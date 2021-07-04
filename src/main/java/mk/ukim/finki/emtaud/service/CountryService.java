package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> save(String name, String continent);
    Optional<Country> findById(Long id);
    void delete(Long id);
}
