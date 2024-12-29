package com.example.demo.Controller;

import com.example.demo.Mapper.ShopMapper;
import com.example.demo.en.Clerk;
import com.example.demo.en.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopMapper shopMapper;

    // 查看所有店铺信息
    @GetMapping("/allshops")
    public List<Shop> getAllShops() {
        return shopMapper.selectList();
    }
    // 根据 id 查找店铺信息
    @GetMapping("/getshopById/{shopId}")
    public Shop getShopById(@PathVariable("shopId") int shopId) {
        return shopMapper.selectById(shopId);
    }

    // 修改店铺信息
    @PutMapping("/updateshop/{shopId}")
    public Shop updateShop(@PathVariable("shopId") int shopId, @RequestBody Shop shop) {
        Shop existingShop = shopMapper.selectById(shopId);
        if (existingShop!= null) {
            shop.setShopId(shopId);
            shopMapper.updateById(shop);
            return shop;
        } else {
            return null;
        }
    }

    // 新增店铺信息
    @PostMapping("/createshop")
    public Shop createShop(@RequestBody Shop shop) {
        shopMapper.insert(shop);
        return shop;
    }

    // 删除店铺信息
    @DeleteMapping("/deleteshop/{shopId}")
    public String deleteShop(@PathVariable("shopId") int shopId) {
        shopMapper.deleteById(shopId);
        return "店铺 ID: " + shopId + " 已删除";
    }

    // 设为默认店铺
    @PutMapping("/setDefault/{shopId}")
    public String setShopAsDefault(@PathVariable("shopId") int shopId) {
        int rowsAffected = shopMapper.setShopAsDefault(shopId);
        if (rowsAffected > 0) {
            return "店铺 ID: " + shopId + " 已设为默认";
        } else {
            return "未找到店铺 ID: " + shopId;
        }
    }


    // 取消店铺默认
    @PutMapping("/cancelDefault/{shopId}")
    public String cancelShopDefault(@PathVariable("shopId") int shopId) {
        int rowsAffected = shopMapper.cancelShopDefault(shopId);
        if (rowsAffected > 0) {
            return "店铺 ID: " + shopId + " 已取消默认";
        } else {
            return "未找到店铺 ID: " + shopId;
        }
    }
    // 获取默认店铺（moren 为 1 的店铺）信息
    @GetMapping("/getDefaultShops")
    public List<Shop> getDefaultShops() {
        return shopMapper.selectByMoren();
    }
}