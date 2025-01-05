package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.Model.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    Admin CreateAdmine(Admin admin);

    Admin getAdminById(Long id);

    List<Admin> getAllAdmins();

    Admin updateAdmin(Long id ,Admin admin);

    void deleteAdmin(Long id);

}
