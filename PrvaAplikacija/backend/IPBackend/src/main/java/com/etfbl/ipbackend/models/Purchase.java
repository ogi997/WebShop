package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "purchase", schema = "ipdb", catalog = "")
public class Purchase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_use")
    private Integer fkUse;
    @Basic
    @Column(name = "fk_product_purchase")
    private Integer fkProductPurchase;
    @Basic
    @Column(name = "fk_payment")
    private Integer fkPayment;
    @Basic
    @Column(name = "card_number")
    private String cardNumber;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkUse() {
        return fkUse;
    }

    public void setFkUse(int fkUse) {
        this.fkUse = fkUse;
    }

    public void setFkUse(Integer fkUse) {
        this.fkUse = fkUse;
    }

    public int getFkProductPurchase() {
        return fkProductPurchase;
    }

    public void setFkProductPurchase(int fkProductPurchase) {
        this.fkProductPurchase = fkProductPurchase;
    }

    public void setFkProductPurchase(Integer fkProductPurchase) {
        this.fkProductPurchase = fkProductPurchase;
    }

    public int getFkPayment() {
        return fkPayment;
    }

    public void setFkPayment(int fkPayment) {
        this.fkPayment = fkPayment;
    }

    public void setFkPayment(Integer fkPayment) {
        this.fkPayment = fkPayment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


}
