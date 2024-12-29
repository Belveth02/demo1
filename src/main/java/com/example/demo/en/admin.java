package com.example.demo.en;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("admin")
public class admin {
    @TableId(value = "adminid",type = IdType.AUTO)
    private Integer adminid;
    private String adminpass;
    private String adminname;
    private String adminsex;
}
