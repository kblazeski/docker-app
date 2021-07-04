package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.repository.CountryRepository;
import mk.ukim.finki.emtaud.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        return Optional.of(this.countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        this.countryRepository.deleteById(id);
    }
}
