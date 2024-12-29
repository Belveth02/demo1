package com.example.demo.Controller;
import com.example.demo.Mapper.CartMapper;
import com.example.demo.en.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
public class CartController {
    @Autowired
    private CartMapper cartMapper;
    @GetMapping("/cart")
    public List<Cart> getcart(){  return cartMapper.selectList(null); }
    @PostMapping("/addcart")
    public Cart createcart(@RequestBody Cart cart) {
        cartMapper.insert(cart);
        return cart;
    }
    @DeleteMapping("/delcart/{id}")
    public int delcart(@PathVariable("id") int id) {
        return cartMapper.deleteById(id);
    }
    @PutMapping("/updatecart/{id}")
    public Cart updateCart(@PathVariable("id") int id, @RequestBody Cart cart) {
        Cart existingUser = cartMapper.selectById(id);
        if (existingUser != null) {
            cart.setCartid(id);
            cartMapper.updateById(cart);
            return cart;
        } else {
            return null;
        }
    }

}
