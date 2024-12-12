package org.example;

public class Ingredients {
    String name, measurementAmount, noConvertibleAmount;
    double quantity, noConvertibleQuantity;

    Ingredients() {

    }

    Ingredients(String name, double quantity, String measurementAmount, String noConvertibleAmount, double noConvertibleQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.measurementAmount = measurementAmount;
        this.noConvertibleAmount = noConvertibleAmount;
        this.noConvertibleQuantity = noConvertibleQuantity;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasurementAmount() {
        return measurementAmount;
    }

    public String getNoConvertibleAmount() {
        return noConvertibleAmount;
    }

    public double getNoConvertibleQuantity() {
        return noConvertibleQuantity;
    }

    public void setNoConvertibleAmount(String noConvertibleAmount) {
        this.noConvertibleAmount = noConvertibleAmount;
    }

    public void setNoConvertibleQuantity(double noConvertibleQuantity) {
        this.noConvertibleQuantity = noConvertibleQuantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasurementAmount(String measurementAmount) {
        this.measurementAmount = measurementAmount;
    }
}