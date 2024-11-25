package com.eternity.bystro;

import static com.eternity.bystro.BystroDatabase.CREATE_CART_LIST_TABLE;
import static com.eternity.bystro.BystroDatabase.CREATE_ORDER_TABLE;
import static com.eternity.bystro.BystroDatabase.CREATE_PRODUCT_LIST_TABLE;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        BystroDatabase bystrodb = new BystroDatabase(this);

        String[][] productlist = {
                {"headset","red dragon","90000",String.valueOf(R.drawable.headset1),"the best",String.valueOf(100)},
                {"headsetz","purple","100000",String.valueOf(R.drawable.headsetgood),"nice",String.valueOf(25)},
                {"blue headset","ice blue","120000",String.valueOf(R.drawable.blueheadset),"freeze!!!",String.valueOf(33)}
        };

        String[][] productlistwithid = new String[productlist.length][productlist[0].length + 1];
        for (int i = 0; i < productlist.length; i++) {
            productlistwithid[i][0] = String.valueOf(i + 1);
            System.arraycopy(productlist[i], 0, productlistwithid[i], 1, productlist[i].length);
        }

        bystrodb.recreateTable("product_list",CREATE_PRODUCT_LIST_TABLE);
        bystrodb.recreateTable("orders",CREATE_ORDER_TABLE);
        bystrodb.addProduct(productlistwithid);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.order) {
                selectedFragment = new OrdersFragment();
            } else if (item.getItemId() == R.id.profile) {
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    public static String formatIntToRP(int number) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
        return formatter.format(number).replace(",00","");
    }
}
