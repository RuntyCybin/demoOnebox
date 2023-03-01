package com.onebox.demoOneBox.dto;

import com.onebox.demoOneBox.model.Cart;
import com.onebox.demoOneBox.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {
    Cart cart;
    List<Product> cartProducts;
}
