package com.hm2t.quizbugs.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.service.questions.CategoryService;
import com.hm2t.quizbugs.service.questions.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> listCategories(){
        Iterable<Category> listCategories = categoryService.findAllByIsEnabled(1);
            return new ResponseEntity<>(listCategories,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getOneCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createCategory(@Validated @RequestBody Category category, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        if(category !=null)
            categoryService.save(category);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateCategory(@Validated @RequestBody Category category, @PathVariable("id") Long id, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Category> currentCategory = categoryService.findById(id);
        if(currentCategory.isPresent()) {
            currentCategory.get().setIsEnabled(0);
            categoryService.save(category);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        Optional<Category> currentCategory = categoryService.findById(id);
        currentCategory.get().setIsEnabled(0);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
