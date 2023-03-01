package com.onebox.demoOneBox.controller;

import com.onebox.demoOneBox.dto.CartResponseDTO;
import com.onebox.demoOneBox.exception.CartNotFoundException;
import com.onebox.demoOneBox.model.Cart;
import com.onebox.demoOneBox.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/createCart")
    public ResponseEntity<Cart> createCart(
            @RequestBody Cart cart
    ) {
        cartService.createCartCache(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @GetMapping("/getCart")
    public ResponseEntity<CartResponseDTO> getCart(
            @RequestParam(value = "cartId", required = true)
            String cartId
    ) throws CartNotFoundException {
        CartResponseDTO cartResponseDTO = cartService.getCartCache(cartId);
        if (null == cartResponseDTO.getCart() || null == cartResponseDTO.getCartProducts())
            throw new CartNotFoundException("Cart was not found");
        return new ResponseEntity<CartResponseDTO>(cartResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart")
    public ResponseEntity<String> deleteCart(
            @RequestParam(value = "cartId", required = true)
            String cartId
    ) throws CartNotFoundException {
        if (cartService.deleteCartCache(cartId)) {
            return new ResponseEntity<>("Cart was deleted", HttpStatus.OK);
        } else {
            throw new CartNotFoundException("Cart to delete was not found");
        }
    }
}
