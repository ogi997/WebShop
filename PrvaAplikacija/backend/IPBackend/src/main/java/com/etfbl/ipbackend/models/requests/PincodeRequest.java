package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;

public class PincodeRequest {
    @NotBlank
    private Integer pinCode;

    public Integer getPinCode() {
        return pinCode;
    }
}
