package com.etfbl.ipbackend.models.requests;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Integer fkAttribute;

    public String getName() {
        return name;
    }

    public Integer getFkAttribute() {
        return fkAttribute;
    }
}
