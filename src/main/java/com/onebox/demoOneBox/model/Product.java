package com.onebox.demoOneBox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    private String description;

    private int amount;

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
