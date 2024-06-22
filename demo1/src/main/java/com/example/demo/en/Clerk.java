package com.example.demo.en;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@TableName("clerk")
public class Clerk {
    @TableId(type = IdType.AUTO)
    private Integer clerkid;
    private String clerkname;
    private String clerkpassword; // 假设这是密码字段
    private String sex;
    private String phone;
    private Date hiredate;
    private BigDecimal salary;
    private String level;

    public String getPassword() {
        return clerkpassword;
    }
    public void setPassword(String password) {
        this.clerkpassword = password;
    }

}

