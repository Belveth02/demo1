package com.example.demo.Controller;
import com.example.demo.en.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // 获取所有地址
    @GetMapping
    public ResponseEntity<List<Address>> getAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    // 根据 ID 获取单个地址
    @GetMapping("/{addressid}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer addressid) {
        Address address = addressService.getAddressById(addressid);
        return address != null ? ResponseEntity.ok(address) : ResponseEntity.notFound().build();
    }

    // 新增地址
    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        boolean success = addressService.addAddress(address);
        return success ? ResponseEntity.ok(address) : ResponseEntity.badRequest().build();
    }

    // 编辑地址
    @PutMapping("/{addressid}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer addressid, @RequestBody Address address) {
        address.setAddressId(addressid); // 确保地址ID被正确传递
        boolean success = addressService.updateAddress(address);
        return success ? ResponseEntity.ok(address) : ResponseEntity.notFound().build();
    }

    // 删除地址
    @DeleteMapping("/{addressid}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer addressid) {
        boolean success = addressService.deleteAddress(addressid);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // 根据用户ID获取地址
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Address>> getAddressByUserId(@PathVariable Integer userid) {
        List<Address> addresses = addressService.getAddressByUserId(userid);
        return addresses != null && !addresses.isEmpty()
                ? ResponseEntity.ok(addresses)
                : ResponseEntity.notFound().build();
    }
}
