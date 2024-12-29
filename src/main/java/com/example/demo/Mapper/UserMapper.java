package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.en.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {}