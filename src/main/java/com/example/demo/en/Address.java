package com.example.demo.en;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("addresses")
public class Address {
    @TableId
    private Integer addressid;  // 地址ID
    private String userid;      // 用户ID
    private String address;     // 地址内容
    private boolean isDefault;  // 是否是默认地址

    public void setAddressId(Integer addressid) {
        this.addressid = addressid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
