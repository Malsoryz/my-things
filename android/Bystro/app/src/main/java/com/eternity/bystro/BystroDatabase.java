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
    public long addProduct(String name, String type, String price, int image, String desc, int stock) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("type",type);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("stock",stock);

        long result = db.insert("product_list",null,values);
        return result;
    }
    public Cursor getAllProduct(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM product_list;";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
