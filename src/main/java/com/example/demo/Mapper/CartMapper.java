package com.example.demo.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.Cart;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}