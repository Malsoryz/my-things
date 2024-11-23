package com.eternity.bystro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private BystroDatabase bystrodb;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        GridLayout listorder = rootview.findViewById(R.id.listorder);

        bystrodb = new BystroDatabase(getContext());
        listorder.removeAllViews(); // Pastikan tidak ada elemen lama
        Cursor cursor = bystrodb.getAllProduct();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("productid"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                    String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                    int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
                    String desc = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                    int stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));

                    View listitem = inflater.inflate(R.layout.homeitem, listorder, false);
                    ImageView photoframe = listitem.findViewById(R.id.photoframe);
                    TextView productname = listitem.findViewById(R.id.homeproductname);
                    TextView productprice = listitem.findViewById(R.id.homeproductprice);

                    photoframe.setImageResource(image);
                    photoframe.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                        int width = photoframe.getWidth();
                        photoframe.getLayoutParams().height = width;
                        photoframe.requestLayout();
                    });

                    productname.setText(name);
                    productprice.setText(price);

                    listorder.addView(listitem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            cursor.close(); // Pastikan cursor ditutup
        }

        return rootview;
    }


}
