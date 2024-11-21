package com.eternity.bystro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ArrayList<productdata> databystro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databystro = new ArrayList<>();

        databystro.add(new productdata("headset",80000,"pro max","this is a good headset",R.drawable.headset1));
        databystro.add(new productdata("headset",80000,"pro max","this is a best headset",R.drawable.headset1));

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

    public ArrayList<productdata> getDatabystro() {
        return databystro;
    }
}
