package com.onebox.demoOneBox.service;

import com.onebox.demoOneBox.model.Cart;
import com.onebox.demoOneBox.model.Product;
import com.onebox.demoOneBox.repository.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private Cache<Product> productCache;

    @Autowired
    public ProductService(Cache<Product> product) {
        this.productCache = product;
    }

    public void createProductCache(Product product) {
        productCache.put(product.getProductid().toString(), product, 10);
    }

    public List<Product> getProductsByCart(Cart cart) {
        return productCache.getAllProductsByCartId(cart.getId().toString());
    }

    public Boolean deleteProduct(Cart cart) {
        List<Product> products = productCache.getAllProductsByCartId(cart.getCartname());
        for(Product prd : products) {
            if (!productCache.remove(prd.getProductid().toString())) {
                return false;
            }
        }
        return true;
    }

}
