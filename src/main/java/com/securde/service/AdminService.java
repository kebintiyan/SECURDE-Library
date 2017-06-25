package com.securde.service;

import com.securde.model.account.Admin;
import com.securde.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by kevin on 6/25/2017.
 */

@Service("adminService")
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public void saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }
}
