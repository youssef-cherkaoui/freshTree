package com.zalarfresh.ZalarFresh.Service.Impl;

import com.zalarfresh.ZalarFresh.Model.Admin;
import com.zalarfresh.ZalarFresh.Repository.AdminRepository;
import com.zalarfresh.ZalarFresh.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;



    @Override
    public Admin CreateAdmine(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Admin non trouvé"));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {

        Admin existAdmine = adminRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("admin non trouvé"));
        existAdmine.setNom(admin.getNom());
        existAdmine.setEmail(admin.getEmail());
        existAdmine.setMotdepasse(admin.getMotdepasse());
        existAdmine.setTelephone(admin.getTelephone());

        return adminRepository.save(existAdmine);
    }

    @Override
    public void deleteAdmin(Long id) {
         adminRepository.deleteById(id);
    }
}
