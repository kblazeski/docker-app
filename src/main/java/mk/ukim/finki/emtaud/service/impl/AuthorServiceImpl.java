package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.repository.AuthorRepository;
import mk.ukim.finki.emtaud.repository.CountryRepository;
import mk.ukim.finki.emtaud.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long id) {
        Optional<Country> country = this.countryRepository.findById(id);
        Author author = new Author(name,surname,country.get());
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).get();
        Author author = this.authorRepository.findById(id).get();
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }
}
