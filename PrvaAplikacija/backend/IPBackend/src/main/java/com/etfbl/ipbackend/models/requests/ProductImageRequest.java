package com.etfbl.ipbackend.models.requests;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductImageRequest {
    @NotBlank
    private Integer fkProductId;
    @NotBlank
    private String name;
    @NotBlank
    private Byte cover;

    public Integer getFkProductId() {
        return fkProductId;
    }

    public String getName() {
        return name;
    }

    public Byte getCover() {
        return cover;
    }
}
