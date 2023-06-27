package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "message", schema = "ipdb", catalog = "")
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_u")
    private Integer fkU;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "status")
    private Byte status;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkU() {
        return fkU;
    }

    public void setFkU(int fkU) {
        this.fkU = fkU;
    }

    public void setFkU(Integer fkU) {
        this.fkU = fkU;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(fkU, message.fkU) && Objects.equals(text, message.text) && Objects.equals(status, message.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkU, text, status);
    }
}
