package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
    @Select("SELECT * FROM shops")
    List<Shop> selectList();

    @Select("SELECT * FROM shops WHERE shopId = #{shopId}")
    Shop selectById(int shopId);

    @Update("UPDATE shops SET shopName = #{shopName}, address = #{address}, phone = #{phone}, description = #{description}, openTime = #{openTime}, closeTime = #{closeTime}, category = #{category} WHERE shopId = #{shopId}")
    int updateById(Shop shop);

    @Insert("INSERT INTO shops (shopName, address, phone, description, openTime, closeTime, category) VALUES (#{shopName}, #{address}, #{phone}, #{description}, #{openTime}, #{closeTime}, #{category})")
    int insert(Shop shop);

    @Delete("DELETE FROM shops WHERE shopId = #{shopId}")
    void deleteById(int shopId);

    // 根据 shopId 更新 moren 字段为 1（设为默认）
    @Update("UPDATE shops SET moren = 1 WHERE shopId = #{shopId}")
    int setShopAsDefault(int shopId);


    // 根据 shopId 更新 moren 字段为 0（取消默认）
    @Update("UPDATE shops SET moren = 0 WHERE shopId = #{shopId}")
    int cancelShopDefault(int shopId);

    // 根据 moren 字段值查询店铺信息（获取 moren 为 1 的店铺）
    @Select("SELECT * FROM shops WHERE moren = 1")
    List<Shop> selectByMoren();
}