package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "attribute_value", schema = "ipdb", catalog = "")
public class AttributeValue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "value")
    private String value;
    @Basic
    @Column(name = "fk_attribute_id")
    private Integer fkAttributeId;
    @Basic
    @Column(name = "fk_proizvod")
    private Integer fkProizvod;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeValue that = (AttributeValue) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    public int getFkAttributeId() {
        return fkAttributeId;
    }

    public void setFkAttributeId(Integer fkAttributeId) {
        this.fkAttributeId = fkAttributeId;
    }

    public void setFkAttributeId(int fkAttributeId) {
        this.fkAttributeId = fkAttributeId;
    }

    public int getFkProizvod() {
        return fkProizvod;
    }

    public void setFkProizvod(Integer fkProizvod) {
        this.fkProizvod = fkProizvod;
    }

    public void setFkProizvod(int fkProizvod) {
        this.fkProizvod = fkProizvod;
    }
}
