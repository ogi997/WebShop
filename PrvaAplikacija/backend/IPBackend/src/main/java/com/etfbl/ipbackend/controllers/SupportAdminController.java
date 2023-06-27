package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.SupportAdmin;
import com.etfbl.ipbackend.models.requests.SupportAdminRequest;
import com.etfbl.ipbackend.services.SupportAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/support-admins")
public class SupportAdminController {
    private final SupportAdminService supportAdminService;

    public SupportAdminController(SupportAdminService supportAdminService) {
        this.supportAdminService = supportAdminService;
    }

    @PostMapping
    public ResponseEntity<SupportAdmin> createSupportAdmin(@RequestBody SupportAdminRequest supportAdminRequest) {
        SupportAdmin supportAdmin = supportAdminService.createSupportAdmin(supportAdminRequest);

        return ResponseEntity.ok(supportAdmin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportAdmin> getSupportAdminById(@PathVariable("id") Integer id) {
        Optional<SupportAdmin> supportAdminOptional = supportAdminService.getSupportAdminById(id);
        return supportAdminOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SupportAdmin>> getAllSupportAdmin() {
        List<SupportAdmin> supportAdmins = supportAdminService.getSupportAdmins();

        return ResponseEntity.ok(supportAdmins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportAdmin> updateSupportAdmin(@PathVariable("id") Integer id, @RequestBody SupportAdminRequest supportAdminRequest) {
        try {
            SupportAdmin supportAdmin = supportAdminService.updateSupportAdmin(id, supportAdminRequest);

            return ResponseEntity.ok(supportAdmin);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportAdmin(@PathVariable("id") Integer id) {
        supportAdminService.deleteSupportAdmin(id);

        return ResponseEntity.noContent().build();
    }
}
