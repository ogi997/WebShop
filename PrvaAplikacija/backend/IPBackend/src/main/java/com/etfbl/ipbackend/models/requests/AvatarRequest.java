package com.etfbl.ipbackend.models.requests;

import lombok.Data;

@Data
public class AvatarRequest {

    private Integer fkUserAvatar;
    private String name;

    public Integer getFkUserAvatar() {
        return fkUserAvatar;
    }

    public String getName() {
        return name;
    }
}
