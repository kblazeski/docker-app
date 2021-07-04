package mk.ukim.finki.emtaud.repository;

import mk.ukim.finki.emtaud.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
