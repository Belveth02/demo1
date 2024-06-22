package com.example.demo.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.CoffMapper;
import com.example.demo.en.Coff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CoffeeController {
    @Autowired
    private CoffMapper coffMapper;

    @GetMapping("/coffee")
    public List<Coff> getcoff(){  return coffMapper.selectList(null); }
    //添加咖啡
    @PostMapping("/addcoffee")
    public int addcoffee(@RequestBody Coff cafe) {
        return coffMapper.insert(cafe);
    }
    //修改已有的咖啡
    @PutMapping("/updatecoffee/{cfid}")
    public Coff updacoffee(@PathVariable("cfid") int cfid,
                             @RequestBody Coff cafe) {
        Coff existingcoffee = coffMapper.selectById(cfid);
        if (existingcoffee != null) {
            cafe.setCfid(cfid);
            coffMapper.updateById(cafe);
            return cafe;
        } else {
            return null;
        }
    }
    @GetMapping("/searchcoff/{name}")
    public List<Coff> searchCartByName(@PathVariable("name") String name) {
        return coffMapper.selectByCfName(name);
    }
    //模糊查询
    @GetMapping("/coffeebyname/{cfname}")
    public List<Coff> findByname(@PathVariable String cfname){
        QueryWrapper<Coff> queryWrapper =new QueryWrapper<>();
        queryWrapper.like("cfname",cfname);
        return coffMapper.selectList(queryWrapper);
    }
    //分页
    @GetMapping("/cofffindByPage")
    public IPage getUserList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        Page<Coff> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Coff> queryWrapper = new QueryWrapper<>();
//queryWrapper.orderByDesc("cfid"); // 根据id字段降序排序
// page.addOrder(OrderItem.desc("cfid")); // 添加降序排序条件
        IPage ipage = coffMapper.selectPage(page,null);
        return ipage;
    }
    //删除咖啡
    @DeleteMapping("/delcoffee/{cfid}")
    public int delcoffee(@PathVariable int cfid) {
        return coffMapper.deleteById(cfid);
    }
}