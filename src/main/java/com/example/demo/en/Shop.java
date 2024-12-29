package com.example.demo.en;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@TableName("shop")
public class Shop {
    @TableId(value = "shopId",type = IdType.AUTO)
    private int shopId;
    private String shopName;
    private String address;
    private String phone;
    private String description;
    private String openTime;
    private String closeTime;
    private String category;
    private int moren;

    public void setMoren(int moren) {
        this.moren = moren;
    }
    // 生成相应的 getter 和 setter 方法
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}