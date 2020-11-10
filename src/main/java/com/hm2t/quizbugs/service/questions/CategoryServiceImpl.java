package com.hm2t.quizbugs.service.questions;

import com.hm2t.quizbugs.model.questions.Category;
import com.hm2t.quizbugs.model.questions.Question;
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
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.of(categoryRepository.getOne(id));
    }

    @Override
    public Category save(Category category) {
        categoryRepository.save(category);
        return null;
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Long findTopOrderByIdDesc() {
        return categoryRepository.findTopOrderByIdDesc();
    }

    @Override
    public Iterable<Category> findAllByIsEnabled(int isEnabled) {
        return categoryRepository.findAllByIsEnabled(isEnabled);
    }
}
