package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product", schema = "ipdb", catalog = "")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "fk_category")
    private Integer fkCategory;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "fk_user")
    private Integer fkUser;
    @Basic
    @Column(name = "contact")
    private String contact;
    @Basic
    @Column(name = "condition_product")
    private String conditionProduct;
    @Basic
    @Column(name = "deleted")
    private Byte deleted;

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(int fkCategory) {
        this.fkCategory = fkCategory;
    }

    public void setFkCategory(Integer fkCategory) {
        this.fkCategory = fkCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getConditionProduct() {
        return conditionProduct;
    }

    public void setConditionProduct(String conditionProduct) {
        this.conditionProduct = conditionProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(description, product.description) && Objects.equals(fkCategory, product.fkCategory) && Objects.equals(price, product.price) && Objects.equals(location, product.location) && Objects.equals(fkUser, product.fkUser) && Objects.equals(contact, product.contact) && Objects.equals(conditionProduct, product.conditionProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, fkCategory, price, conditionProduct, location, fkUser, contact);
    }
}
