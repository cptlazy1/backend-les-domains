package com.std.springtechdomain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
// This class is necessary for Post and Put methodes
public class CIModuleInputDto {
    @NotNull(message = "Naam is vereist.")
    public String name;
    public String type;
    @Positive
    public Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
