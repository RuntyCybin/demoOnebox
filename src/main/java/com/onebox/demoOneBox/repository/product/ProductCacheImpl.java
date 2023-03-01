package com.onebox.demoOneBox.repository.product;

import com.onebox.demoOneBox.model.Product;
import com.onebox.demoOneBox.repository.Cache;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductCacheImpl implements Cache<Product> {

    private HashMap<String, Object[]> cache = new HashMap<String, Object[]>();

    @Override
    public void put(String key, Product value, long ttl) {
        ttl = System.currentTimeMillis() + ttl * 1000;
        if (key == null) throw new RuntimeException("Product cannot be null!");
        cache.put(key, new Object[]{ttl, value});
    }

    @Override
    public Product get(String key) {
        if (cache.containsKey(key)) {
            Long expires = (Long) cache.get(key)[0];
            if (expires - System.currentTimeMillis() > 0) {
                return (Product) cache.get(key)[1];
            }
        }
        return null;
    }

    public List<Product> getAllProductsByCartId(String cartId) {
        List<Product> resultList = new ArrayList<>();
        cache.forEach(
                (key, value) -> {
                    Product prod = (Product) value[1];
                    if (prod.getCart().equalsIgnoreCase(cartId)) {
                        resultList.add(prod);
                    }
                }
        );

        return resultList.size() > 0 ? resultList : null;
    }

    public boolean remove(String key) {
        return removeAndGet(key) != null;
    }

    public Object removeAndGet(String key) {
        Object entry = cache.remove(key);
        //System.out.println("entry=" + entry);
        if (entry != null) {
            return entry;
        }
        return null;
    }
}
