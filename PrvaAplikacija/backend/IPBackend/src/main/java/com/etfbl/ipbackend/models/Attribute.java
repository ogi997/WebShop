package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "attribute", schema = "ipdb", catalog = "")
public class Attribute {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "fk_category_id")
    private Integer fkCategoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFkCategoryId() {
        return fkCategoryId;
    }

    public void setFkCategoryId(Integer fkCategoryId) {
        this.fkCategoryId = fkCategoryId;
    }

    public void setFkCategoryId(int fkCategoryId) {
        this.fkCategoryId = fkCategoryId;
    }
}
