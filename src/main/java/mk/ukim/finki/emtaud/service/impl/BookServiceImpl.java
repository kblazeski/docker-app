package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.exception.InvalidAuthorException;
import mk.ukim.finki.emtaud.model.exception.InvalidBookException;
import mk.ukim.finki.emtaud.repository.AuthorRepository;
import mk.ukim.finki.emtaud.repository.BookRepository;
import mk.ukim.finki.emtaud.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) throws InvalidAuthorException {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new InvalidAuthorException(bookDto.getAuthor()));
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) throws InvalidBookException, InvalidAuthorException {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new InvalidBookException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new InvalidAuthorException(bookDto.getAuthor()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) throws InvalidBookException {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new InvalidBookException(id));
        if(book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies()-1);
        }
        return Optional.of(this.bookRepository.save(book));
    }
}
