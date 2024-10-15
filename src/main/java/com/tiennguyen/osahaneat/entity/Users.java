package com.tiennguyen.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="fullname")
    private String fullname;
    @Column(name="create_date")
    private Date createDate;


    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles roles;

    @OneToMany(mappedBy = "users")
    private Set<RatingFood> lisRatingFood;
    @OneToMany(mappedBy = "users")
    private Set<RatingRestaurant> listRatingRestaurant;

    @OneToMany(mappedBy = "users")
    private Set<Orders> listOrder;

    public Set<Orders> getListOrder() {
        return listOrder;
    }

    public void setListOrder(Set<Orders> listOrder) {
        this.listOrder = listOrder;
    }

    public Set<RatingRestaurant> getListRatingRestaurant() {
        return listRatingRestaurant;
    }

    public void setListRatingRestaurant(Set<RatingRestaurant> listRatingRestaurant) {
        this.listRatingRestaurant = listRatingRestaurant;
    }

    public Set<RatingFood> getLisRatingFood() {
        return lisRatingFood;
    }

    public void setLisRatingFood(Set<RatingFood> lisRatingFood) {
        this.lisRatingFood = lisRatingFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
