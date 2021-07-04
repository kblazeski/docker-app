package mk.ukim.finki.emtaud.web.restcontroller;

import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.model.exception.InvalidAuthorException;
import mk.ukim.finki.emtaud.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries(){
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable Long id){
        return this.countryService.findById(id).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){

        this.countryService.delete(id);
        if(this.countryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestParam String name, @RequestParam String continent) throws InvalidAuthorException {
        return this.countryService.save(name,continent)
                .map(country ->  ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
