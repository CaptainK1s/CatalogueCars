package com.example.catalogue;

public class Animal {
    private String name;
    private String description;
    private int imageResourceId;
    private int thumbnailResourceId;

    public Animal(String name, String description, int imageResourceId, int thumbnailResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.thumbnailResourceId = thumbnailResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getThumbnailResourceId() {
        return thumbnailResourceId;
    }
}
