package com.etfbl.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageRequest {
    @NotBlank
    private Integer fkU;
    @NotBlank
    private String text;
    @NotBlank
    private Byte status;

    public Integer getFkU() {
        return fkU;
    }

    public String getText() {
        return text;
    }

    public Byte getStatus() {
        return status;
    }
}
