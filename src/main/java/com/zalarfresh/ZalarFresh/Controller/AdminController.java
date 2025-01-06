package com.zalarfresh.ZalarFresh.Controller;

import com.zalarfresh.ZalarFresh.Model.Admin;
import com.zalarfresh.ZalarFresh.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/createUser")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.CreateAdmine(admin);
    }

    @GetMapping("/ShowUserById")
    public Admin ShowAdminById(@RequestParam Long id) {
        return adminService.getAdminById(id);
    }

    @GetMapping("/ShowAllUsers")
    public List<Admin> ShowAllAdmins() {
        return adminService.getAllAdmins();
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @PutMapping("/editUser/{id}")
    public Admin editAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }
}
