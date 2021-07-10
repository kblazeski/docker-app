package mk.ukim.finki.emtaud.web.restcontroller;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.exception.InvalidAuthorException;
import mk.ukim.finki.emtaud.model.exception.InvalidBookException;
import mk.ukim.finki.emtaud.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors(){
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id){
        return this.authorService.findById(id).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){

        this.authorService.delete(id);
        if(this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addNewAuthor(@RequestParam String name, @RequestParam String surname, @RequestParam Long id) throws InvalidAuthorException {
        return this.authorService.save(name,surname,id)
                .map(author ->  ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id,@RequestParam String name, @RequestParam String surname, @RequestParam Long countryId){
        return this.authorService.edit(id,name,surname,countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
