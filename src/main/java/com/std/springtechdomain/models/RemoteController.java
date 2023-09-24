package com.std.springtechdomain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RemoteController {
    @Id
    @GeneratedValue
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String brand;
    private Double price;
    private Integer originalStock;

    // Default constructor
    public RemoteController() {
    }

    // Constructor
    public RemoteController(Long id, String compatibleWith, String batteryType, String brand, Double price, Integer originalStock) {
        this.id = id;
        this.compatibleWith = compatibleWith;
        this.batteryType = batteryType;
        this.brand = brand;
        this.price = price;
        this.originalStock = originalStock;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }
}