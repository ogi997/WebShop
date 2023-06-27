package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "supply", schema = "ipdb", catalog = "")
public class Supply {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_prod")
    private Integer fkProd;
    @Basic
    @Column(name = "active")
    private Byte active;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkProd() {
        return fkProd;
    }

    public void setFkProd(int fkProd) {
        this.fkProd = fkProd;
    }

    public void setFkProd(Integer fkProd) {
        this.fkProd = fkProd;
    }


    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply supply = (Supply) o;
        return Objects.equals(id, supply.id) && Objects.equals(fkProd, supply.fkProd) && Objects.equals(active, supply.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkProd, active);
    }
}
