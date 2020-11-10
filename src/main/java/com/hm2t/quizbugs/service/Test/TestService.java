package com.hm2t.quizbugs.service.Test;

import com.hm2t.quizbugs.model.Test.Test;
import com.hm2t.quizbugs.service.IService;

public interface TestService extends IService<Test> {
    Iterable<Test> findAllByEnabledTrue();
}
