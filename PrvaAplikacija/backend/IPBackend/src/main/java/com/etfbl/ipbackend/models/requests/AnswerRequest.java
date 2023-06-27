package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnswerRequest {
    @NotBlank
    private Integer fkQuestion;
    @NotBlank
    private Integer fkUserAnsKo;
    @NotBlank
    private String value;
    @NotBlank
    private Integer fkUserAnsKome;

    public Integer getFkQuestion() {
        return fkQuestion;
    }

    public Integer getFkUserAnsKo() {
        return fkUserAnsKo;
    }

    public String getValue() {
        return value;
    }

    public Integer getFkUserAnsKome() {
        return fkUserAnsKome;
    }
}
