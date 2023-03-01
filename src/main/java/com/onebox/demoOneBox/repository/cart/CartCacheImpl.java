package com.onebox.demoOneBox.repository.cart;

import com.onebox.demoOneBox.model.Cart;
import com.onebox.demoOneBox.model.Product;
import com.onebox.demoOneBox.repository.Cache;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CartCacheImpl implements Cache<Cart> {
    private HashMap<String, Object[]> cache = new HashMap<String, Object[]>();

    @Override
    public void put(String key, Cart value, long ttl) {
        ttl = System.currentTimeMillis() + ttl * 1000;
        if (key == null) throw new RuntimeException("Cart cannot be null!");
        cache.put(key, new Object[]{ttl, value});
    }

    @Override
    public Cart get(String key) {
        if (cache.containsKey(key)) {
            Long expires = (Long) cache.get(key)[0];
            if (expires - System.currentTimeMillis() > 0) {
                return (Cart) cache.get(key)[1];
            } else {
                remove(key);
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProductsByCartId(String cartId) {
        return null;
    }

    public boolean remove(String key) {
        return removeAndGet(key) != null;
    }

    public Object removeAndGet(String key) {
        Object entry = cache.remove(key);
        if (entry != null) {
            return entry;
        }
        return null;
    }
}
