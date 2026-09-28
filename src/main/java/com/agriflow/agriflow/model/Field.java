package com.agriflow.agriflow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Field area must be greater than 0")
    private double area;

    @NotBlank(message = "Crop season is required")
    private String cropSeason;

    @Positive(message = "Growth duration must be greater than 0")
    private Integer growthDuration;

    @NotBlank(message = "Soil type is required")
    private String soilType;

    @NotNull(message = "Farm is required")
    @ManyToOne
    private Farm farm;

    @NotNull(message = "Crop is required")
    @ManyToOne
    private Crop crop;


    public Long getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(String cropSeason) {
        this.cropSeason = cropSeason;
    }

    public Integer getGrowthDuration() {
        return growthDuration;
    }

    public void setGrowthDuration(Integer growthDuration) {
        this.growthDuration = growthDuration;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}