package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "question", schema = "ipdb", catalog = "")
public class Question {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_product")
    private Integer fkProduct;
    @Basic
    @Column(name = "value")
    private String value;
    @Basic
    @Column(name = "fk_user_ko")
    private int fkUserKo;
    @Basic
    @Column(name = "fk_user_kome")
    private int fkUserKome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(int fkProduct) {
        this.fkProduct = fkProduct;
    }

    public void setFkProduct(Integer fkProduct) {
        this.fkProduct = fkProduct;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFkUserKo() {
        return fkUserKo;
    }

    public void setFkUserKo(Integer fkUserKo) {
        this.fkUserKo = fkUserKo;
    }

    public void setFkUserKo(int fkUserKo) {
        this.fkUserKo = fkUserKo;
    }

    public int getFkUserKome() {
        return fkUserKome;
    }

    public void setFkUserKome(Integer fkUserKome) {
        this.fkUserKome = fkUserKome;
    }

    public void setFkUserKome(int fkUserKome) {
        this.fkUserKome = fkUserKome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return fkUserKo == question.fkUserKo && fkUserKome == question.fkUserKome && Objects.equals(id, question.id) && Objects.equals(fkProduct, question.fkProduct) && Objects.equals(value, question.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkProduct, fkUserKo, value, fkUserKome);
    }
}
