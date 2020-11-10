package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.questions.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Iterable<Category> findAllByEnabledTrue();
}
