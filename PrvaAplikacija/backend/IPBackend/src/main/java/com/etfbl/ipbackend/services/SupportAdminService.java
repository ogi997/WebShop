package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.SupportAdmin;
import com.etfbl.ipbackend.models.requests.SupportAdminRequest;

import java.util.List;
import java.util.Optional;

public interface SupportAdminService {
    SupportAdmin createSupportAdmin(SupportAdminRequest supportAdminRequest);

    Optional<SupportAdmin> getSupportAdminById(Integer id);

    List<SupportAdmin> getSupportAdmins();

    SupportAdmin updateSupportAdmin(Integer id, SupportAdminRequest supportAdminRequest) throws ExistingException;

    void deleteSupportAdmin(Integer id);
}
