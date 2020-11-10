package com.hm2t.quizbugs.config.jwt.model;

import lombok.Data;

@Data
public class UpdatePassword {
    private String oldPassword;

    private String newPassword;
}
