package com.model;

import org.springframework.web.multipart.MultipartFile;

public class SpendingForm {
    private Long id;
    private String name;
    private double price;
    private String note;
    private MultipartFile image;
    private Category category;

    public SpendingForm() {
    }

    public SpendingForm(String name, double price, String note, MultipartFile image, Category category) {
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
        this.category = category;
    }

    public SpendingForm(Long id, String name, double price, String note, MultipartFile image, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
        this.category = category;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
