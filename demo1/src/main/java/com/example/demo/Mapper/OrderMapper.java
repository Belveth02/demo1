package com.example.demo.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.Order;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order>{
}