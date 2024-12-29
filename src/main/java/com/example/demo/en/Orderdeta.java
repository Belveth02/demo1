package com.example.demo.en;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("orderdetails")
public class Orderdeta {
    @TableId(value = " orderdetaid", type = IdType.AUTO)
    private Integer orderdetaid;//订单详情id，唯一标识符
    private Integer orderid;//订单id，商品所属订单的id
    private double cfprice;//商品价格
    private String cfimage;//商品图片
    private String cfname;//商品名称
    private String cfsize;//商品大小
    private String cftem;//商品温度
    private String cfsweet;//商品甜度
    private Integer ordercfnum;
}