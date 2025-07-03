package com.rental.dto;

import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requête pour créer ou mettre à jour une location")
public class RentalRequest {

    @Schema(description = "Nom de la location", example = "Bel appartement Saint-Etienne")
    private String name;

    @Schema(description = "Surface en mètres carrés", example = "45.5")
    private Double surface;

    @Schema(description = "Prix en euros", example = "1200.0")
    private Double price;

    @Schema(description = "Photo de la location")
    private MultipartFile picture;

    @Schema(description = "Description détaillée de la location",
            example = "Appartement lumineux avec vue sur le stade Geoffroy Guichard")
    private String description;

    private Long ownerId;

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
