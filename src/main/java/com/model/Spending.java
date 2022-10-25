package com.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Component
public class Spending implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String note;
    private String image;

    @ManyToOne
    private Category category;

    public Spending() {
    }

    public Spending(String name, double price, String note, String image, Category category) {
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
        this.category = category;
    }

    public Spending(Long id, String name, double price, String note, String image, Category category) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Spending.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    Spending spending =(Spending) target;
    String price = String.valueOf(spending.getPrice());
        ValidationUtils.rejectIfEmpty(errors, "price","price.empty");
        if (!price.matches("(^[0-9]*$)")){
            errors.rejectValue("price","price.matches");
        }
    }
}
