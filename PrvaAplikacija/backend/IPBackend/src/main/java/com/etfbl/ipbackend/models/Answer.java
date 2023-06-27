package com.etfbl.ipbackend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "answer", schema = "ipdb", catalog = "")
public class Answer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "fk_question")
    private Integer fkQuestion;
    @Basic
    @Column(name = "fk_user_ans_ko")
    private Integer fkUserAnsKo;
    @Basic
    @Column(name = "value")
    private String value;
    @Basic
    @Column(name = "fk_user_ans_kome")
    private Integer fkUserAnsKome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkQuestion() {
        return fkQuestion;
    }

    public void setFkQuestion(Integer fkQuestion) {
        this.fkQuestion = fkQuestion;
    }

    public Integer getFkUserAnsKo() {
        return fkUserAnsKo;
    }

    public void setFkUserAnsKo(Integer fkUserAnsKo) {
        this.fkUserAnsKo = fkUserAnsKo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getFkUserAnsKome() {
        return fkUserAnsKome;
    }

    public void setFkUserAnsKome(Integer fkUserAnsKome) {
        this.fkUserAnsKome = fkUserAnsKome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) && Objects.equals(fkQuestion, answer.fkQuestion) && Objects.equals(fkUserAnsKo, answer.fkUserAnsKo) && Objects.equals(value, answer.value) && Objects.equals(fkUserAnsKome, answer.fkUserAnsKome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkQuestion, fkUserAnsKo, value, fkUserAnsKome);
    }
}
