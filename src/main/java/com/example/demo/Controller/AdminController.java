package com.example.demo.Controller;

import com.example.demo.Mapper.AdminMapper;
import com.example.demo.en.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminMapper adminMapper;

    //查看//
    @GetMapping("/allcoffee")
    public List<admin> getcoffee() {
        return adminMapper.selectList(null);
    }
    //根据id查找
    @GetMapping("/getadminById/{adminid}")
    public admin getUserById(@PathVariable("adminid") int id) {
        return adminMapper.selectById(id);
    }


    //修改
    @PutMapping("/updateadmin/{adminid}")
    public admin updaadmin(@PathVariable("adminid") int cfid,
                             @RequestBody admin admin) {
        admin existingcoffee = adminMapper.selectById(cfid);
        if (existingcoffee != null) {
            admin.setAdminid(cfid);
            adminMapper.updateById(admin);
            return admin;
        } else {
            return null;
        }
    }
    //根据id查找adminname
    @GetMapping("/getAdminNameById/{adminid}")
    public String getAdminNameById(@PathVariable("adminid") int id) {
        admin existingAdmin = adminMapper.selectById(id);
        if (existingAdmin != null) {
            return existingAdmin.getAdminname();
        } else {
            return "未找到ID为 " + id + " 的管理人员";
        }
    }
}



