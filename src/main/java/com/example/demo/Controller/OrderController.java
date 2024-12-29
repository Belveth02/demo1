package com.example.demo.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.OrderMapper;
import com.example.demo.en.Order;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    private OrderService orderService;

    @GetMapping("/order")
    public List<Order> getorder(){  return orderMapper.selectList(null); }
    @PostMapping("/addorder")
    public Order createcart(@RequestBody Order order) {
        orderMapper.insert(order);
        return order;
    }
    //分页
    @GetMapping("order/findByPage")
    public IPage getUserList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
//queryWrapper.orderByDesc("cfid"); // 根据id字段降序排序
// page.addOrder(OrderItem.desc("cfid")); // 添加降序排序条件
        IPage ipage = orderMapper.selectPage(page,null);
        return ipage;
    }
    @GetMapping("/orderbyuser/{orderuserid}")
    public List<Order> findByname(@PathVariable String orderuserid){
        QueryWrapper<Order> queryWrapper =new QueryWrapper<>();
        queryWrapper.like("orderuserid",orderuserid);
        return orderMapper.selectList(queryWrapper);
    }
    @DeleteMapping("/delbyorderid/{orderid}")
    public int deleteOrder(@PathVariable("orderid") Integer orderid) {
        return orderMapper.deleteById(orderid);
    }
    //删除
    @DeleteMapping("/orderdel/{orderuserid}")
    public int delcoffee(@PathVariable int cfid) {
        return orderMapper.deleteById(cfid);
    }
    @PutMapping("/orderup/{orderid}")
    public Order updateOrder(@PathVariable("orderid") Integer orderid, @RequestBody Order order) {
        order.setOrderid(orderid);
        orderMapper.updateById(order);
        return order;
    }
    @GetMapping("/findByorderuserid")
    public Map<String, Object> findByorderuserid(
            @RequestParam(value = "orderuserid") String orderuserid,
            @RequestParam(value = "orderBy", required = false, defaultValue = "orderid") String orderBy,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (orderuserid != null && !orderuserid.isEmpty()) {
            queryWrapper.eq("orderuserid", orderuserid);
        }

        if ("desc".equals(order)) {
            queryWrapper.orderByDesc(orderBy);
        } else {
            queryWrapper.orderByAsc(orderBy);
        }

        List<Order> orders = orderMapper.selectList(queryWrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("records", orders);
        result.put("total", orders.size());
        return result;
    }
    @PutMapping("/updatePayStatus/{orderid}")
    public Order updatePayStatus(@PathVariable("orderid") Integer orderid) {
        // 根据订单ID查询订单
        Order order = orderMapper.selectById(orderid);
        if (order!= null) {
            // 修改支付状态为已支付
            order.setPaystatus("已支付");
            // 更新订单
            orderMapper.updateById(order);
        }
        return order;
    }
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        log.info("收到支付成功通知");
        String out_trade_no = request.getParameter("out_trade_no");
        log.info("流水号：{}", out_trade_no);
        if (out_trade_no!= null) {
            try {
                Integer orderid = Integer.parseInt(out_trade_no);
                updatePayStatus(orderid);
            } catch (NumberFormatException e) {
                log.error("订单ID解析错误", e);
            }
        }
        return "success";
    }
    @GetMapping("/getPayStatus/{orderid}")
    public String getPayStatus(@PathVariable Integer orderid) {
        Order order = orderMapper.selectById(orderid);
        if (order == null) {
            log.info("订单不存在，订单ID: {}", orderid);
            return "订单不存在";
        }
        log.info("查询到订单支付状态，订单ID: {}, 支付状态: {}", orderid, order.getPaystatus());
        return order.getPaystatus();
    }
}
