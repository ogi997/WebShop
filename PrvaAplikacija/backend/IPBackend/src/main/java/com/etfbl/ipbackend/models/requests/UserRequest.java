package com.etfbl.ipbackend.models.requests;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String city;

    private String avatar;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private Byte active;
    @NotBlank
    private Integer pinCode;

    private Byte deleted;

    public Byte getDeleted() {return deleted;}

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public Byte getActive() {
        return active;
    }

    public Integer getPinCode() {
        return pinCode;
    }

}
