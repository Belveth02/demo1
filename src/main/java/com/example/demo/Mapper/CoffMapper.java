package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.Coff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CoffMapper extends BaseMapper<Coff> {
    @Select("SELECT * FROM coffee WHERE cfname LIKE concat('%', #{name}, '%')")
    List<Coff> selectByCfName(String name);

    @Select("SELECT cfname as 商品名称, cfnum as 商品数量 FROM coffee")
    List<Map<String, Object>> countCoffNum();
    @Update("UPDATE coffee SET cfnum = cfnum - #{reduceNum} WHERE cfid = #{cfid} AND cfnum >= #{reduceNum}")
    int updateStockById(@Param("cfid") int cfid, @Param("reduceNum") int reduceNum);
    @Update("UPDATE coffee SET cfnum = cfnum + #{increaseNum} WHERE cfid = #{cfid}")
    int updateStockIncreaseById(@Param("cfid") int cfid, @Param("increaseNum") int increaseNum);
}