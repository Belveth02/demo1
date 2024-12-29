package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.Clerk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClerkMapper extends BaseMapper<Clerk> {
    // 增加打卡天数的方法
    @Update("update clerk set checkdays = checkdays + 1 where clerkid = #{clerkId}")
    // 增加打卡天数的方法
    int increaseCheckDaysById(Integer clerkId);
    // 查询最近一次打卡日期的方法
    @Select("select last_check_date from clerk where clerkid = #{clerkId}")
    LocalDate findLastCheckDateById(Integer clerkId);
    // 更新最近一次打卡日期的方法
    @Update("update clerk set last_check_date = #{checkDate} where clerkid = #{clerkId}")
    void updateLastCheckDateById(Integer clerkId, LocalDate checkDate);

    @Select("SELECT clerkname as 员工姓名, checkdays as 打卡天数 FROM clerk")
    List<Map<String, Object>> clerkdaynum();
}
