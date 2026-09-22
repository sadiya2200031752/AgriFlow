package com.agriflow.agriflow.model;

public class WaterAnalysis {

    private double availableWater;
    private double requiredWater;
    private double usedWater;
    private String status;

    public double getAvailableWater() {
        return availableWater;
    }

    public void setAvailableWater(double availableWater) {
        this.availableWater = availableWater;
    }

    public double getRequiredWater() {
        return requiredWater;
    }

    public void setRequiredWater(double requiredWater) {
        this.requiredWater = requiredWater;
    }

    public double getUsedWater() {
        return usedWater;
    }

    public void setUsedWater(double usedWater) {
        this.usedWater = usedWater;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}