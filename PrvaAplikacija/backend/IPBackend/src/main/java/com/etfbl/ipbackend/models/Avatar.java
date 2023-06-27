package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "avatar", schema = "ipdb", catalog = "")
public class Avatar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_user_avatar")
    private Integer fkUserAvatar;
    @Basic
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkUserAvatar() {
        return fkUserAvatar;
    }

    public void setFkUserAvatar(Integer fkUserAvatar) {
        this.fkUserAvatar = fkUserAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id == avatar.id && Objects.equals(fkUserAvatar, avatar.fkUserAvatar) && Objects.equals(name, avatar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkUserAvatar, name);
    }
}
