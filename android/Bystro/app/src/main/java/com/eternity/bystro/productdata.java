package com.eternity.bystro;

public class productdata {
    private final String productname;
    private final float price;
    private final String type;
    private final String desc;
    private final int photolink;

    public productdata(String productname, float price, String type, String desc, int photolink) {
        this.productname = productname;
        this.price = price;
        this.type = type;
        this.desc = desc;
        this.photolink = photolink;
    }

    public String getProductname() {
        return productname;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public int getPhotolink() {
        return photolink;
    }
}

