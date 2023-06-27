package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PurchaseRequest {
    @NotBlank
    private Integer fkUse;
    @NotBlank
    private Integer fkProductPurchase;
    @NotBlank
    private Integer fkPayment;

    private String cardNumber;

    public Integer getFkUse() {
        return fkUse;
    }

    public Integer getFkProductPurchase() {
        return fkProductPurchase;
    }

    public Integer getFkPayment() {
        return fkPayment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

}
