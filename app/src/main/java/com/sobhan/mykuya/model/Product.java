package com.sobhan.mykuya.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("commercialName")
    @Expose
    private String commercialName;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("minPoints")
    @Expose
    private String minPoints;
    @SerializedName("maxPoints")
    @Expose
    private String maxPoints;
    @SerializedName("isTwoWay")
    @Expose
    private Boolean isTwoWay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(String minPoints) {
        this.minPoints = minPoints;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(String maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Boolean getIsTwoWay() {
        return isTwoWay;
    }

    public void setIsTwoWay(Boolean isTwoWay) {
        this.isTwoWay = isTwoWay;
    }


}