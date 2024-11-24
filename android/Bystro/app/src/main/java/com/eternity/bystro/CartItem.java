package com.eternity.bystro;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int cartid;
    private int image;
    private String name;
    private int productid;
    private String types;
    private String prices;
    private int quantity;

    public CartItem(int cartid, int image, String name, int productid, String types, String prices, int quantity) {
        this.cartid = cartid;
        this.image = image;
        this.name = name;
        this.productid = productid;
        this.types = types;
        this.prices = prices;
        this.quantity = quantity;
    }
    public int getCartid() {return cartid;}
    public int getImage() {return image;}
    public String getName() {return name;}
    public int getProductid() {return productid;}
    public String getTypes() {return types;}
    public String getPrices() {return prices;}
    public int getQuantity() {return quantity;}

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProductid(int productid) {
        this.productid = productid;
    }
    public void setTypes(String types) {
        this.types = types;
    }
    public void setPrices(String prices) {
        this.prices = prices;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
