package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttributeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Integer fkAttributeValue;

    public String getName() {
        return name;
    }

    public Integer getFkAttributeValue() {
        return fkAttributeValue;
    }
}
