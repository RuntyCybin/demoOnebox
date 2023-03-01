package com.onebox.demoOneBox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productid;
    private String description;
    private int amount;
    private String cart;
}
