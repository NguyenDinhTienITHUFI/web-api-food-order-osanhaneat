package com.tiennguyen.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;
    @Column(name="image")
    private String image;
    @Column(name="is_freeship")
    private boolean is_freeship;
    @Column(name="time_ship")
    private String timeShip;
    @Column(name="description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_freeship() {
        return is_freeship;
    }

    public void setIs_freeship(boolean is_freeship) {
        this.is_freeship = is_freeship;
    }

    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;

    @OneToMany(mappedBy = "food")
    private Set<RatingFood> listRatingFood;
    @OneToMany(mappedBy = "food")
    private Set<OrderItem> listOrderUtem;

    public Set<OrderItem> getListOrderUtem() {
        return listOrderUtem;
    }

    public void setListOrderUtem(Set<OrderItem> listOrderUtem) {
        this.listOrderUtem = listOrderUtem;
    }

    public Set<RatingFood> getListRatingFood() {
        return listRatingFood;
    }

    public void setListRatingFood(Set<RatingFood> listRatingFood) {
        this.listRatingFood = listRatingFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getTimeShip() {
        return timeShip;
    }

    public void setTimeShip(String timeShip) {
        this.timeShip = timeShip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
