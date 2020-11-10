package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.questions.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "select max(id) from categories",nativeQuery = true)
    Long findTopOrderByIdDesc();

    Iterable<Category> findAllByIsEnabled(int isEnabled);
}
