package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.Coff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CoffMapper extends BaseMapper<Coff> {
    @Select("SELECT * FROM coffee WHERE cfname LIKE concat('%', #{name}, '%')")
    List<Coff> selectByCfName(String name);
}