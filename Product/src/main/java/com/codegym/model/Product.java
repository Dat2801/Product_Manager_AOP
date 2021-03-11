package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "Please fill out from 2 to 30 character")
    private String name;


    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(@Size(min = 2, max = 30, message = "Please fill out from 2 to 30 character") String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }
}
