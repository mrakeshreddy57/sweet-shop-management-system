package com.sugarcube.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sweets")
public class Sweet {
    @Id
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Sweet(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}