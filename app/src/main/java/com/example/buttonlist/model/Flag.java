package com.example.buttonlist.model;

import com.google.gson.annotations.SerializedName;

public class Flag {
    private int id;

    @SerializedName("country")
    private String country;

    @SerializedName("file")
    private String file;

    public Flag(int id, String country, String file) {
        this.id = id;
        this.country = country;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getFile() {
        return file;
    }
}


