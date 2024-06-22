package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.en.Clerk;
import com.example.demo.Mapper.ClerkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/clerk")
public class Clerkcontroller {
    @Autowired
    ClerkMapper clerkMapper;

    //查看
    @GetMapping("/allclerk")
    public List<Clerk> getclerk() {
        return clerkMapper.selectList(null);
    }

    @PutMapping("/updateclerk/{clerkid}")
    public Clerk updacoffee(@PathVariable("clerkid") int clerk,
                             @RequestBody Clerk cafe) {
        Clerk existingcoffee = clerkMapper.selectById(clerk);
        if (existingcoffee != null) {
            cafe.setClerkid(clerk);
            clerkMapper.updateById(cafe);
            return cafe;
        } else {
            return null;
        }
    }
    @DeleteMapping("/delclerk/{clerkid}")
    public int delclerk(@PathVariable int clerkid) {
        return clerkMapper.deleteById(clerkid);
    }

    @GetMapping("/clerkbyname/{clerkname}")
    public List<Clerk> findByname(@PathVariable String clerkname){
        QueryWrapper<Clerk> queryWrapper =new QueryWrapper<>();
        queryWrapper.like("clerkname",clerkname);
        return clerkMapper.selectList(queryWrapper);
    }
    @GetMapping("/getById/{clerkid}")
    public Clerk findByID(@PathVariable("clerkid") Integer clerkid) {
        return clerkMapper.selectById(clerkid);
    }

    @PutMapping("/{clerkid}/changePassword")
    public ResponseEntity<?> changePassword(@PathVariable("clerkid") Integer clerkid, @RequestBody Map<String, String> passwordData) {
        try {
            Clerk existingClerk = clerkMapper.selectById(clerkid);
            if (existingClerk == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到");
            }

            String currentPassword = passwordData.get("currentPassword");
            String newPassword = passwordData.get("newPassword");

            // 验证当前密码（在这里添加你的密码验证逻辑）
            if (!existingClerk.getPassword().equals(currentPassword)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("当前密码不正确");
            }
            // 更新密码（确保对新密码进行哈希处理）
            existingClerk.setPassword(newPassword);
            clerkMapper.updateById(existingClerk);

            return ResponseEntity.ok("密码更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改密码失败: " + e.getMessage());
        }
    }
}




