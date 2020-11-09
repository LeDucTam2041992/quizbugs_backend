package com.hm2t.quizbugs.service.questions;

import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category model) {
        return categoryRepository.save(model);
    }

    @Override
    public void remove(long id) {

    }
}
