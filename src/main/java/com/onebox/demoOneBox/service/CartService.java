package com.onebox.demoOneBox.service;

import com.onebox.demoOneBox.dto.CartResponseDTO;
import com.onebox.demoOneBox.model.Cart;
import com.onebox.demoOneBox.model.Product;
import com.onebox.demoOneBox.repository.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private Cache<Cart> cartCache;
    private Cache<Product> productCache;

    @Autowired
    public CartService(Cache<Cart> cache, Cache<Product> prod) {
        this.cartCache = cache;
        this.productCache = prod;
    }

    public void createCartCache(Cart cart) {
        cartCache.put(cart.getId().toString(), cart, 10);
    }

    public CartResponseDTO getCartCache(String cartName) {
        Cart cart = cartCache.get(cartName);
        List<Product> products = productCache.getAllProductsByCartId(cartName);
        CartResponseDTO cartResponseDTO = new CartResponseDTO(cart, products);
        return cartResponseDTO;
    }

    public Boolean deleteCartCache(String cartId) {
        List<Product> products = productCache.getAllProductsByCartId(cartId);
        for(Product prd : products) {
            if (!productCache.remove(prd.getProductid().toString())) {
                return false;
            }
        }
        return cartCache.remove(cartId);
    }
}
