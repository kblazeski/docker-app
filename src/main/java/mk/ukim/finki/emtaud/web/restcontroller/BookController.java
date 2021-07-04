package mk.ukim.finki.emtaud.web.restcontroller;

import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.enums.Category;
import mk.ukim.finki.emtaud.model.exception.InvalidAuthorException;
import mk.ukim.finki.emtaud.model.exception.InvalidBookException;
import mk.ukim.finki.emtaud.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return this.bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){

        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/taken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) throws InvalidBookException {
        return this.bookService.markAsTaken(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDto bookDto) throws InvalidAuthorException {
       return this.bookService.save(bookDto)
                .map(book ->  ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,@RequestBody BookDto bookDto) throws InvalidAuthorException, InvalidBookException {
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
