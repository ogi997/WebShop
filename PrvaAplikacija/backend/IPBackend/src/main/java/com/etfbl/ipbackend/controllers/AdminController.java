package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Admin;
import com.etfbl.ipbackend.models.requests.AdminRequest;
import com.etfbl.ipbackend.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminRequest adminRequest) {
        Admin admin = adminService.createAdmin(adminRequest);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Integer id) {
        Optional<Admin> adminOptional = adminService.getAdminById(id);
        return adminOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Integer id, @RequestBody AdminRequest adminRequest) {
        try {
            Admin admin = adminService.updateAdmin(id, adminRequest);

            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
