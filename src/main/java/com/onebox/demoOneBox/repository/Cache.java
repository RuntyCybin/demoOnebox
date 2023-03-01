package com.onebox.demoOneBox.repository;

import com.onebox.demoOneBox.model.Product;

import java.util.List;

public interface Cache<T> {
    public void put(String key, T value, long ttl);
    public T get(String key);
    public List<Product> getAllProductsByCartId(String cartId);
    public boolean remove(String key);
    public Object removeAndGet(String key);
}
