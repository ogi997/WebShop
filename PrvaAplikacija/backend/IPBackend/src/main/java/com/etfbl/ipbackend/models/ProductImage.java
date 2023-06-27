package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product_image", schema = "ipdb", catalog = "")
public class ProductImage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_product_id")
    private Integer fkProductId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "cover")
    private Byte cover;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkProductId() {
        return fkProductId;
    }

    public void setFkProductId(int fkProductId) {
        this.fkProductId = fkProductId;
    }

    public void setFkProductId(Integer fkProductId) {
        this.fkProductId = fkProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getCover() {
        return cover;
    }

    public void setCover(byte cover) {
        this.cover = cover;
    }

    public void setCover(Byte cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImage that = (ProductImage) o;
        return Objects.equals(id, that.id) && Objects.equals(fkProductId, that.fkProductId) && Objects.equals(name, that.name) && Objects.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkProductId, name, cover);
    }
}
