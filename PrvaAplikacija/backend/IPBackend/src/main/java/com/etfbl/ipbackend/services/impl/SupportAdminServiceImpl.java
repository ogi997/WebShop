package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.SupportAdmin;
import com.etfbl.ipbackend.models.requests.SupportAdminRequest;
import com.etfbl.ipbackend.repositories.SupportAdminRepository;
import com.etfbl.ipbackend.services.SupportAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportAdminServiceImpl implements SupportAdminService {
    private final SupportAdminRepository supportAdminRepository;

    public SupportAdminServiceImpl(SupportAdminRepository supportAdminRepository) {
        this.supportAdminRepository = supportAdminRepository;
    }

    @Override
    public SupportAdmin createSupportAdmin(SupportAdminRequest supportAdminRequest) {
        SupportAdmin supportAdmin = new SupportAdmin();
        supportAdmin.setFirstName(supportAdminRequest.getFirstName());
        supportAdmin.setLastName(supportAdminRequest.getLastName());
        supportAdmin.setPassword(supportAdminRequest.getPassword());
        supportAdmin.setUsername(supportAdminRequest.getUsername());
        return supportAdminRepository.save(supportAdmin);
    }

    @Override
    public Optional<SupportAdmin> getSupportAdminById(Integer id) {
        return supportAdminRepository.findById(id);
    }

    @Override
    public List<SupportAdmin> getSupportAdmins() {
        return supportAdminRepository.findAll();
    }

    @Override
    public SupportAdmin updateSupportAdmin(Integer id, SupportAdminRequest supportAdminRequest) throws ExistingException {
        Optional<SupportAdmin> supportAdminOptional = supportAdminRepository.findById(id);

        if (supportAdminOptional.isEmpty())
            throw new ExistingException("Support admin sa ID: " + id + " ne postoji u bazi podataka");

        SupportAdmin supportAdmin = supportAdminOptional.get();
        supportAdmin.setFirstName(supportAdminRequest.getFirstName());
        supportAdmin.setLastName(supportAdminRequest.getLastName());
        supportAdmin.setPassword(supportAdminRequest.getPassword());
        supportAdmin.setUsername(supportAdminRequest.getUsername());
        return supportAdminRepository.save(supportAdmin);
    }

    @Override
    public void deleteSupportAdmin(Integer id) {
        supportAdminRepository.deleteById(id);
    }
}
