package com.etfbl.ipbackend.models.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequest {
    @NotBlank
    private Integer fkProduct;
    @NotBlank
    private int fkUserKo;
    @NotBlank
    private int fkUserKome;
    @NotBlank
    private String value;

    public Integer getFkProduct() {
        return fkProduct;
    }

    public int getFkUserKo() {
        return fkUserKo;
    }

    public int getFkUserKome() {
        return fkUserKome;
    }

    public String getValue() {
        return value;
    }
}
