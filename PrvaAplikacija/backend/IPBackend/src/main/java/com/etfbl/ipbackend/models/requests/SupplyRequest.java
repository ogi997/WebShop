package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;

@Data
public class SupplyRequest {
    @NotBlank
    private Integer fkProd;
    @NotBlank
    private Byte active;

    public Integer getFkProd() {
        return fkProd;
    }

    public Byte getActive() {
        return active;
    }
}
