package com.hm2t.quizbugs.repository;

import com.hm2t.quizbugs.model.Test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Long> {
}
