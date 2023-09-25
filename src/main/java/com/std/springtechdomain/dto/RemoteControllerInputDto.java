package com.std.springtechdomain.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
// This class is necessary for Post and Put methodes
public class RemoteControllerInputDto {
    // Valideren van data kan hier geconfigureerd worden

    @NotNull
    public String compatibleWith;
    public String batteryType;
//    @NotNull(message = "Naam is vereist")
//    @Size(max = 64)
    public String brand;
//    @Positive
    public Double price;
    @Positive
    public Integer originalStock;

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }
}
