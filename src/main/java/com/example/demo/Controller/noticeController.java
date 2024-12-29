package com.example.demo.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.en.notice;
import com.example.demo.Mapper.noticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/notice")
public class noticeController {
    @Autowired
    noticeMapper noticeMapper;

    //查看所有
    @GetMapping("/allnotice")
    public List<notice> getnotice() {
        return noticeMapper.selectList(null);
    }

    //添加
    @PostMapping("/addnotice")
    public int addnotice(@RequestBody notice cafe) {
        return noticeMapper.insert(cafe);
    }

    //修改
    @PutMapping("/updatenotice")
    public int updanotice(@RequestBody notice cafe) {
        System.out.printf(String.valueOf(cafe));
           return noticeMapper.updateById(cafe);

    }
    //删除
    @DeleteMapping("/delnotice/{noticeid}")
    public int delnotice(@PathVariable int noticeid) {
        return noticeMapper.deleteById(noticeid);
    }
    //搜索
    @GetMapping("/coffeebyname/{massage}")
    public List<notice> findByname(@PathVariable String massage){
        QueryWrapper<notice> queryWrapper =new QueryWrapper<>();
        queryWrapper.like("massage",massage);
        return noticeMapper.selectList(queryWrapper);
    }
    //分页
    @GetMapping("/findByPage")
    public IPage getUserList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        Page<notice> page = new Page<>(pageNum, pageSize);
        QueryWrapper<notice> queryWrapper = new QueryWrapper<>();
        //queryWrapper.orderByDesc("cfid"); // 根据id字段降序排序
        // page.addOrder(OrderItem.desc("cfid")); // 添加降序排序条件
        IPage ipage = noticeMapper.selectPage(page,null);
        return ipage;
    }

}



