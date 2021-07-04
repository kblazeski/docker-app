package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.exception.InvalidAuthorException;
import mk.ukim.finki.emtaud.model.exception.InvalidBookException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto) throws InvalidAuthorException;

    Optional<Book> edit(Long id,BookDto bookDto) throws InvalidBookException, InvalidAuthorException;

    void delete(Long id);

    Optional<Book> markAsTaken(Long id) throws InvalidBookException;

}
