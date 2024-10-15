package com.tiennguyen.osahaneat.dto;

public class MenuDTO {
    private int id;
    private String title;
    private String image;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private boolean is_freeship;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIs_freeship() {
        return is_freeship;
    }

    public void setIs_freeship(boolean is_freeship) {
        this.is_freeship = is_freeship;
    }
}
