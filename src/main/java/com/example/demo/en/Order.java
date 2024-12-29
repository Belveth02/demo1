package com.example.demo.en;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("orderdemo")
public class Order {
    @TableId(value = " orderid", type = IdType.AUTO)
    private Integer orderid;//订单编号
    private Integer orderuserid;//订单用户id
    private double totalprice;//总价
    private String userphone;//用户号码
    private String donestatus;//订单完成状态
    private String paystatus;//支付状态
    private String address;//地址
    private String ordertime; // 下单时间
    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus;
    }
}

