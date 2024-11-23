package com.eternity.bystro;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ArrayList<ProductData> databystro;

    private BystroDatabase bystrodb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bystrodb = new BystroDatabase(this);
        long result = bystrodb.addProduct("headset","red dragon","90000",R.drawable.headsetgood,"the best",100);

        databystro = new ArrayList<>();

        databystro.add(new ProductData("headset", 80000,"pro max", "this is a good headset", R.drawable.headset1));
        databystro.add(new ProductData("blue headset", 95000,"gaming", "this is a best headset", R.drawable.headsetgood));

        ImageButton tocart = findViewById(R.id.tocart);
        tocart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewCart.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

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
    public ArrayList<ProductData> getDatabystro() {
        return databystro;
    }
}
