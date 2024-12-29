package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.en.Clerk;
import com.example.demo.Mapper.ClerkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    //根据姓名对店员进行查找
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
    //添加店员
    @PostMapping("/addclerk")
    public int addclerk(@RequestBody Clerk clerk) {
        return clerkMapper.insert(clerk);
    }
//    //编辑店员信息
//    @PutMapping("/updateclerk/{clerkid}")
//    public Clerk updaclerk(@PathVariable("clerkid") int clerkid,
//                           @RequestBody Clerk clerk) {
//        Clerk existingclerk = clerkMapper.selectById(clerkid);
//        if (existingclerk != null) {
//            clerk.setClerkid(clerkid);
//            clerkMapper.updateById(clerk);
//            return clerk;
//        } else {
//            return null;
//        }
//    }
    //分页
    @GetMapping("/clerkByPage")
    public IPage getUserList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        Page<Clerk> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Clerk> queryWrapper = new QueryWrapper<>();
//queryWrapper.orderByDesc("cfid"); // 根据id字段降序排序
// page.addOrder(OrderItem.desc("cfid")); // 添加降序排序条件
        IPage ipage = clerkMapper.selectPage(page,null);
        return ipage;
    }
    //打开
    @Transactional
    @PutMapping("/increaseCheckDays/{clerkId}")
    public ResponseEntity<String> increaseCheckDays(@PathVariable Integer clerkId) {
        // 输出日志
        System.out.println("Increasing check days for clerkId: " + clerkId);
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 查询该用户最近一次打卡日期
        LocalDate lastCheckDate = clerkMapper.findLastCheckDateById(clerkId);
        if (lastCheckDate!= null && lastCheckDate.isEqual(today)) {
            return ResponseEntity.status(400).body("今日已经打卡，不能重复打卡");
        }
        // 执行打卡操作，增加打卡天数
        int result = clerkMapper.increaseCheckDaysById(clerkId);
        // 输出执行结果
        System.out.println("Update result: " + result);
        if (result > 0) {
            // 更新最近一次打卡日期
            clerkMapper.updateLastCheckDateById(clerkId, today);
            return ResponseEntity.ok("打卡天数增加成功");
        } else {
            return ResponseEntity.status(500).body("打卡天数增加失败");
        }
    }
    //获取打卡天数
    @GetMapping("/checkdaynum")
    public List<Map<String,Object>>numimage(){
        return clerkMapper.clerkdaynum();
    }

    // 分页获取员工信息
    @GetMapping("/getByPage")
    public ResponseEntity<IPage<Clerk>> getEmployeesByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Clerk> pageParam = new Page<>(page, size);
        IPage<Clerk> clerkPage = clerkMapper.selectPage(pageParam, null);
        return new ResponseEntity<>(clerkPage, HttpStatus.OK);
    }

    // 清零或减少所有员工的打卡次数
    @PutMapping("/adjustCheckdays")
    @Transactional
    public ResponseEntity<Void> adjustCheckdays(@RequestParam(value = "operation", required = true) String operation,
                                                @RequestParam(value = "days", required = false) Integer days) {
        List<Clerk> clerks = clerkMapper.selectList(null);
        for (Clerk clerk : clerks) {
            if ("clear".equals(operation)) {
                clerk.setCheckdays(0);
            } else if ("subtract".equals(operation) && days!= null) {
                int newCheckdays = Math.max(0, clerk.getCheckdays() - days);
                clerk.setCheckdays(newCheckdays);
            }
            clerkMapper.updateById(clerk);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}




