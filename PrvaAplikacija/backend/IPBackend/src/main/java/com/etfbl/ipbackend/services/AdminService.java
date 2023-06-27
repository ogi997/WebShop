package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Admin;
import com.etfbl.ipbackend.models.requests.AdminRequest;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin createAdmin(AdminRequest adminRequest);

    Optional<Admin> getAdminById(Integer id);

    List<Admin> getAllAdmins();

    Admin updateAdmin(Integer id, AdminRequest adminRequest) throws ExistingException;

    void deleteAdmin(Integer id);
}
