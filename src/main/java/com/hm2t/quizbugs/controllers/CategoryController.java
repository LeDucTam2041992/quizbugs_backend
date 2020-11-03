package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.service.questions.CategoryService;
import com.hm2t.quizbugs.service.questions.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("questions/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listCategories(){
            return new ResponseEntity<>(getListCategories(),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getOneCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        if(category !=null)
            categoryService.save(category);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCategory(@RequestBody Category category, @PathVariable("id") Long id) {
        Optional<Category> currentCategory = categoryService.findById(id);
        if(currentCategory.isPresent())
            categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        Optional<Category> currentCategory = categoryService.findById(id);
        if(currentCategory.isPresent())
            categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private List<Category> getListCategories(){
        return categoryService.findAll();
    }
}
