package com.example.demo.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Mapper.OrderdetaMapper;
import com.example.demo.en.Orderdeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
public class OrderdetaController {
    @Autowired
    private OrderdetaMapper orderdetaMapper;
    @GetMapping("/orderdeta")
    public List<Orderdeta> getcOrderdeta(){  return orderdetaMapper.selectList(null); }
    @PostMapping("/addorderdeta")
    public Orderdeta createOrderdeta(@RequestBody Orderdeta orderdeta) {
        orderdetaMapper.insert(orderdeta);
        return orderdeta;
    }
    // 新增端点用于根据orderid获取cfname
    @GetMapping("/cfname/{orderid}")
    public List<String> getCfnameByOrderId(@PathVariable Integer orderid) {
        QueryWrapper<Orderdeta> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderid", orderid);
        List<Orderdeta> orderdetas = orderdetaMapper.selectList(queryWrapper);

// 处理多条记录的情况
        List<String> cfNames = new ArrayList<>();
        for (Orderdeta orderdeta : orderdetas) {
            cfNames.add(orderdeta.getCfname());
        }

        return cfNames;
    }
    @GetMapping("/findorderdeta/{orderid}")
    public List<Orderdeta> getOrderdetaByOrderId(@PathVariable Integer orderid) {
        QueryWrapper<Orderdeta> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderid", orderid);
        return orderdetaMapper.selectList(queryWrapper);
    }
}