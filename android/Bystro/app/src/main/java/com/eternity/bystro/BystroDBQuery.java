package com.eternity.bystro;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BystroDBQuery {

    public static final String CREATE_PRODUCT_LIST_TABLE =
            "CREATE TABLE product_list ("+
                    "productid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "name VARCHAR(200), "+
                    "type VARCHAR(50), "+
                    "price VARCHAR(7), "+
                    "image INTEGER, "+
                    "description TEXT, "+
                    "stock INTEGER);";

    public static final String CREATE_CART_LIST_TABLE =
            "CREATE TABLE cart_list ("+
                    "cartid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "productid INTEGER, "+
                    "quantity INTEGER, "+
                    "FOREIGN KEY (productid) REFERENCES product_list(productid));";

    public static final String CREATE_ORDER_TABLE =
            "CREATE TABLE orders ("+
                    "orderid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "productid INTEGER, "+
                    "status VARCHAR(50), "+
                    "FOREIGN KEY (productid) REFERENCES product_list(productid));";

    public static final String SELECT_FOR_CART =
            "SELECT cart_list.cartid, cart_list.productid, cart_list.quantity, " +
                    "product_list.name AS productname, product_list.type, product_list.price " +
                    "FROM cart_list " +
                    "INNER JOIN product_list ON cart_list.productid = product_list.productid;";

    public static final String SELECT_FOR_ORDERS_PAGE =
            "SELECT product_list.productid," +
                    "product_list.name, " +
                    "product_list.type, " +
                    "product_list.price, " +
                    "product_list.image, " +
                    "orders.orderid, " +
                    "orders.quantity, " +
                    "orders.status " +
                    "FROM product_list " +
                    "INNER JOIN orders ON product_list.id = orders.productid" +
                    "ORDER BY orders.orderid;";
}
