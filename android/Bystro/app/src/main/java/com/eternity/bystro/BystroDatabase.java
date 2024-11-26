package com.eternity.bystro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BystroDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bystro.db";
    private static final int DATABASE_VERSION = 1;
    public static final String CREATE_PRODUCT_LIST_TABLE =
            "CREATE TABLE product_list ("+
                    "productid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "name VARCHAR(200), "+
                    "type VARCHAR(50), "+
                    "price VARCHAR(7), "+
                    "image INTEGER, "+
                    "description TEXT, "+
                    "stock INTEGER);";
    public static final String CREATE_ORDER_TABLE =
            "CREATE TABLE orders ("+
                    "orderid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "productid INTEGER, "+
                    "quantity INTEGER, "+
                    "status VARCHAR(50), "+
                    "FOREIGN KEY (productid) REFERENCES product_list(productid));";
    public static final String CREATE_ADDRESS_TABLE =
            "CREATE TABLE address ("+
                    "addressid INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "username VARCHAR(50), "+
                    "useraddress TEXT);";

    public BystroDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCT_LIST_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product_list");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS address");
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
            values.put("productid", productid);
            values.put("name", name);
            values.put("type", type);
            values.put("price", price);
            values.put("image", image);
            values.put("description", desc);
            values.put("stock", quantity);

            db.insert("product_list", null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void addNewAddress(String username, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("useraddress",address);
        db.insert("address", null, values);
    }
    public Cursor getTableValues(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableName;
        return db.rawQuery(query, null);
    }
    public void recreateTable(String tableName, String createTableQuery) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL(createTableQuery);
    }
}
