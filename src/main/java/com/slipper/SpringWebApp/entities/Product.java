package com.slipper.SpringWebApp.entities;

import javax.persistence.*;

// Сущность ПРОДУКТ
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private int price;

    @Column(name="description")
    private String description;

    public Product() { }

    public Product(Long id, String title, int price, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("Product: [id=%d, title=%s, price=%d, descr=%s]", id, title, price, description);
    }
}
