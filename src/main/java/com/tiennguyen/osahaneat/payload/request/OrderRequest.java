package com.tiennguyen.osahaneat.payload.request;

public class OrderRequest {
    private int userID;
    private int restID;
    private int[] foodIDs;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRestID() {
        return restID;
    }

    public void setRestID(int restID) {
        this.restID = restID;
    }

    public int[] getFoodIDs() {
        return foodIDs;
    }

    public void setFoodIDs(int[] foodIDs) {
        this.foodIDs = foodIDs;
    }
}
