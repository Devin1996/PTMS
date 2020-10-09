package com.example.ptms.Model;

public class DatabasePromotions {
    private String desc;
    private String image;

    public DatabasePromotions(String desc , String image) {
        this.desc = desc;
        this.image = image;
    }

    public DatabasePromotions() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
