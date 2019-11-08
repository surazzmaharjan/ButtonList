package com.example.buttonlist.model;

public class Food {
    private String foodname;
    private String category;
    private String rate;
    private int image;

    public Food(String foodname, String category, String rate, int image) {
        this.foodname = foodname;
        this.category = category;
        this.rate = rate;
        this.image = image;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getCategory() {
        return category;
    }

    public String getRate() {
        return rate;
    }

    public int getImage() {
        return image;
    }
}
