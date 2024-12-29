package com.example.demo.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Mapper.OrderMapper;
import com.example.demo.Mapper.OrderdetaMapper;
import com.example.demo.en.Order;
import com.example.demo.en.Orderdeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderdetaMapper orderDetailsMapper;

    public List<Order> getOrdersByUserId(Integer userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderuserid", userId);
        return orderMapper.selectList(queryWrapper);
    }

    public List<Orderdeta> getOrderDetailsByOrderId(Integer orderId) {
        QueryWrapper<Orderdeta> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderid", orderId);
        return orderDetailsMapper.selectList(queryWrapper);
    }
    public Order updatePayStatus(Integer orderId) {
        // 根据订单ID查询订单
        Order order = orderMapper.selectById(orderId);
        if (order!= null) {
            // 修改支付状态为已支付
            order.setPaystatus("已支付");
            // 更新订单
            orderMapper.updateById(order);
        }
        return order;
    }
}