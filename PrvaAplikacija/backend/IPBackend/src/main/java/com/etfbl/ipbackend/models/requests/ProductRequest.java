package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Integer fkCategory;
    @NotBlank
    private Double price;
    @NotBlank
    private String conditionProduct;
    @NotBlank
    private String location;
    @NotBlank
    private Integer fkUser;
    @NotBlank
    private String contact;
    private Byte deleted;

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFkCategory() {
        return fkCategory;
    }

    public Double getPrice() {
        return price;
    }

    public String getConditionProduct() {
        return conditionProduct;
    }

    public String getLocation() {
        return location;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public String getContact() {
        return contact;
    }
}
