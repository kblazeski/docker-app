package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.exception.InvalidAuthorException;
import mk.ukim.finki.emtaud.model.exception.InvalidBookException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> findAll();
    Optional<Author> save(String name,String surname,Long id);
    Optional<Author> findById(Long id);
    void delete(Long id);
}
