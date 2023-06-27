package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttributeValueReqeust {
    @NotBlank
    private String value;
    @NotBlank
    private Integer fkAttributeId;
    @NotBlank
    private Integer fkProizvod;

    public Integer getFkAttributeId() {
        return fkAttributeId;
    }

    public Integer getFkProizvod() {
        return fkProizvod;
    }

    public String getValue() {
        return value;
    }
}
