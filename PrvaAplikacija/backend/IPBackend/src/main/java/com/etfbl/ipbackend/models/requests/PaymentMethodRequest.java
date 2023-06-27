package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentMethodRequest {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
