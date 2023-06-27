package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Admin;
import com.etfbl.ipbackend.models.requests.AdminRequest;
import com.etfbl.ipbackend.repositories.AdminRepository;
import com.etfbl.ipbackend.services.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin createAdmin(AdminRequest adminRequest) {
        Admin admin = new Admin();
        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());
        admin.setUsername(adminRequest.getUsername());
        admin.setPassword(adminRequest.getPassword());
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> getAdminById(Integer id) {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Integer id, AdminRequest adminRequest) throws ExistingException {
        Optional<Admin> optionalExistingAdmin = adminRepository.findById(id);
        Admin existingAdmin;
        if (optionalExistingAdmin.isEmpty())
            throw new ExistingException("Admin sa ID: " + id + " ne postoji u bazi podataka");

        existingAdmin = optionalExistingAdmin.get();

        existingAdmin.setFirstName(adminRequest.getFirstName());
        existingAdmin.setLastName(adminRequest.getLastName());
        existingAdmin.setUsername(adminRequest.getUsername());
        existingAdmin.setPassword(adminRequest.getPassword());
        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }
}
