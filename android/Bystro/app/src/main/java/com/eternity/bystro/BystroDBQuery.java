package com.eternity.bystro;

public class BystroDBQuery {

    public static final String CREATE_PRODUCT_LIST_TABLE =
            "CREATE TABLE product_list ("+
                    "productid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "name VARCHAR(200), "+
                    "type VARCHAR(50), "+
                    "price DOUBLE, "+
                    "desc TEXT, "+
                    "stock INTEGER);";

    public static final String CREATE_ORDER_TABLE =
            "CREATE TABLE orders ("+
                    "orderid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "productid INTEGER, "+
                    "quantity INTEGER, "+
                    "status VARCHAR(50), "+
                    "FOREIGN KEY (productid) REFERENCES product_list(productid));";
}
