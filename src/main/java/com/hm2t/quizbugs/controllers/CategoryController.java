package com.hm2t.quizbugs.controllers;

import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.service.catogories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Iterable<Category>> listCategories(){
        Iterable<Category> listCategories = categoryService.findAllByIsEnabledTrue();
            return new ResponseEntity<>(listCategories,HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Optional<Category>> getOneCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> createCategory(@Validated @RequestBody Category category, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        if(category !=null)
            categoryService.save(category);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PutMapping("{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> updateCategory(@Validated @RequestBody Category category, @PathVariable("id") Long id, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Category> currentCategory = categoryService.findById(id);
        if(currentCategory.isPresent()) {
            category.setId(id);
            categoryService.save(category);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        Optional<Category> currentCategory = categoryService.findById(id);
        if(currentCategory.isPresent()) {
            currentCategory.get().setEnabled(false);
            categoryService.save(currentCategory.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
