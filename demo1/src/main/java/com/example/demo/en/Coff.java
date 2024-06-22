package com.example.demo.en;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coffee")
public class Coff {
    @TableId(value = " cfid", type = IdType.AUTO)
    private Integer cfid;//商品id
    private double cfprice;//商品价格
    private String cfimage;//商品图片
    private String cfname;//商品名称
    private String cfdes;//商品描述
    private String cfsize;//商品大小
    private String cftem;//商品温度
    private String cfsweet;//商品甜度
    private String cftype;//商品类型
    private String hot;//是否热门
    private Integer cfnum;//商品id
}