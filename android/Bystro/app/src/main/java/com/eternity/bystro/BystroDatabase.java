package com.eternity.bystro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BystroDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bystro.db";
    private static final int DATABASE_VERSION = 1;

    public BystroDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BystroDBQuery.CREATE_PRODUCT_LIST_TABLE);
        db.execSQL(BystroDBQuery.CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product_list");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }
    public void addProduct(String[][] productlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        for (String[] product : productlist) {
            int productid = Integer.parseInt(product[0]);
            String name = product[1];
            String type = product[2];
            String price = product[3];
            int image = Integer.parseInt(product[4]);
            String desc = product[5];
            int quantity = Integer.parseInt(product[6]);

            ContentValues values = new ContentValues();
            values.put("productid",productid);
            values.put("name",name);
            values.put("type",type);
            values.put("price",price);
            values.put("image",image);
            values.put("description",desc);
            values.put("stock",quantity);

            db.insert("product_list",null,values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void addtocart(String[][] cartlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        for (String[] product : cartlist) {
            int productid = Integer.parseInt(product[0]);
            int quantity = Integer.parseInt(product[1]);

            ContentValues values = new ContentValues();
            values.put("productid",productid);
            values.put("quantity",quantity);

            db.insert("cart_list",null,values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public Cursor getTableValue(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableName + ";";
        return db.rawQuery(query,null);
    }
    public Cursor getTableValueforCart() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT cart_list.cartid, cart_list.productid, cart_list.quantity, " +
                "product_list.name, product_list.type, product_list.image, product_list.price " +
                "FROM cart_list " +
                "INNER JOIN product_list ON cart_list.productid = product_list.productid;";
        return db.rawQuery(query,null);
    }
    public void recreateTable(String tableName, String createTableQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL(createTableQuery);
    }

}
