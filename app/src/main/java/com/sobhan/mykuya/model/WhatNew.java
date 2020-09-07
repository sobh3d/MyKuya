package com.sobhan.mykuya.model;

public class WhatNew {
    private String title;
    private String des;
    private String image;

    public WhatNew(String title, String des, String image) {
        this.title = title;
        this.des = des;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
