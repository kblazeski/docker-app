package mk.ukim.finki.emtaud.web.restcontroller;

import mk.ukim.finki.emtaud.model.enums.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

//    @GetMapping
//    public List<Category> getAllCategories(){
//        return Arrays.asList(Category.values());
//    }
}
