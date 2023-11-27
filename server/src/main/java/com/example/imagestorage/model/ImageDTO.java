package com.example.imagestorage.model;

public class ImageDTO {
    private String name;

    public ImageDTO() {

    }

    public ImageDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
