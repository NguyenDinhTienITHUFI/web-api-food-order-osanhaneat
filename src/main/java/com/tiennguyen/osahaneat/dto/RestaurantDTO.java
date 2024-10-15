package com.tiennguyen.osahaneat.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
    private int id;
    private String image;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeship;
    private Date openDate;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    List<CategoryDTO> categorys;



    public List<CategoryDTO> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryDTO> categorys) {
        this.categorys = categorys;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }
}
