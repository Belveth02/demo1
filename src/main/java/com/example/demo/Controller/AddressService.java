package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.en.Address;
import com.example.demo.Mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    // 获取所有地址
    public List<Address> getAllAddresses() {
        return addressMapper.selectList(null);
    }

    // 根据 ID 获取地址
    public Address getAddressById(Integer addressid) {
        return addressMapper.selectById(addressid);
    }

    // 新增地址
    public boolean addAddress(Address address) {
        return addressMapper.insert(address) > 0;
    }

    // 编辑地址
    public boolean updateAddress(Address address) {
        return addressMapper.updateById(address) > 0;
    }

    // 删除地址
    public boolean deleteAddress(Integer addressid) {
        return addressMapper.deleteById(addressid) > 0;
    }
    // 根据用户ID获取所有地址
    public List<Address> getAddressByUserId(Integer userid) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return addressMapper.selectList(queryWrapper);
    }
}
