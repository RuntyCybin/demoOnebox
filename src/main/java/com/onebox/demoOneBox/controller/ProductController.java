package com.onebox.demoOneBox.controller;

import com.onebox.demoOneBox.model.Product;
import com.onebox.demoOneBox.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/productCart")
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product
    ) {
        productService.createProductCache(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }


}
