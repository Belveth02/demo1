package com.example.demo.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.en.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/user")
    public List<User> getUsers(){  return userMapper.selectList(null); }
    @PostMapping("/adduser")
    public User createUser(@RequestBody User user) {
        userMapper.insert(user);
        return user;
    }
    @PostMapping("/adminadduser")
    public int adduser(@RequestBody User user) {
        return userMapper.insert(user);
    }
    //删除咖啡
    @DeleteMapping("/deluser/{userid}")
    public int deluser(@PathVariable int userid) {
        return userMapper.deleteById(userid);
    }
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userMapper.selectById(id);
    }

    //模糊查询
    @GetMapping("/userbyname/{username}")
    public List<User> findByname(@PathVariable String username){
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.like("username",username);
        return userMapper.selectList(queryWrapper);
    }
    //分页
    @GetMapping("/findByPage")
    public IPage getUserList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage ipage = userMapper.selectPage(page,null);
        return ipage;
    }

    @PutMapping("/updateuser/{userid}")
    public User updaUser(@PathVariable("userid") int userid,
                         @RequestBody User user) {
        User existingUser = userMapper.selectById(userid);
        if (existingUser != null) {
            user.setUserid(userid);
            userMapper.updateById(user);
            return user;
        } else {
            return null;
        }
    }
    @PutMapping("/{userid}/changePassword")
    public ResponseEntity<?> changePassword(@PathVariable("userid") Integer userid, @RequestBody Map<String, String> passwordData) {
        try {
            User existingUser = userMapper.selectById(userid);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到");
            }

            String currentPassword = passwordData.get("currentPassword");
            String newPassword = passwordData.get("newPassword");

            // 验证当前密码（在这里添加你的密码验证逻辑）
            if (!existingUser.getPassword().equals(currentPassword)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("当前密码不正确");
            }
            // 更新密码（确保对新密码进行哈希处理）
            existingUser.setPassword(newPassword);
            userMapper.updateById(existingUser);

            return ResponseEntity.ok("密码更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改密码失败: " + e.getMessage());
        }
    }
}