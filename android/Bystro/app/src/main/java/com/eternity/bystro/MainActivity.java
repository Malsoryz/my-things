package com.eternity.bystro;

import static com.eternity.bystro.BystroDatabase.CREATE_ADDRESS_TABLE;
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
                {"Glidex Phantom","GX-300","249000",String.valueOf(R.drawable.mouse1),"Glidex Phantom GX-300 adalah mouse gaming ergonomis dengan desain futuristik yang memadukan kenyamanan dan performa tinggi. Mouse ini dirancang khusus untuk gamer yang membutuhkan akurasi, kecepatan, dan daya tahan dalam sesi permainan panjang. Dengan pencahayaan LED biru pada scroll wheel dan logo, GX-300 tidak hanya berfungsi dengan baik, tetapi juga terlihat memukau di meja Anda.\n\nTombol tambahan di sisi samping memberikan akses cepat untuk fungsi shortcut atau kontrol dalam game, sehingga meningkatkan respons dan efisiensi. Dibuat dengan material matte berkualitas tinggi, mouse ini memberikan genggaman yang nyaman sekaligus tahan lama.",String.valueOf(129)},
                {"Auraluxe","Zenith 500","335000",String.valueOf(R.drawable.headsetgood),"Auraluxe Zenith 500 adalah headphone premium yang dirancang untuk pecinta musik sejati yang menginginkan pengalaman audio imersif dan desain modern. Dengan kombinasi warna ungu metalik dan hitam matte, headphone ini tidak hanya memberikan performa luar biasa, tetapi juga menghadirkan estetika yang memukau. Cocok untuk digunakan dalam berbagai kondisi, baik saat santai di rumah, bepergian, maupun bekerja.\n\nEar cup besar dengan bantalan kulit sintetis berkualitas tinggi memastikan kenyamanan bahkan setelah berjam-jam pemakaian. Headband fleksibel memberikan penyesuaian optimal untuk berbagai ukuran kepala tanpa mengorbankan daya tahan.\n\nTombol kontrol pada ear cup kanan memudahkan pengguna untuk mengatur volume, memutar/menghentikan musik, atau menerima panggilan tanpa perlu mengakses perangkat utama. Ditambah dengan mikrofon bawaan, Zenith 500 juga ideal untuk panggilan telepon atau konferensi virtual.",String.valueOf(25)}
        };

        String[][] productlistwithid = new String[productlist.length][productlist[0].length + 1];
        for (int i = 0; i < productlist.length; i++) {
            productlistwithid[i][0] = String.valueOf(i + 1);
            System.arraycopy(productlist[i], 0, productlistwithid[i], 1, productlist[i].length);
        }

        bystrodb.recreateTable("product_list",CREATE_PRODUCT_LIST_TABLE);
        bystrodb.recreateTable("address",CREATE_ADDRESS_TABLE);
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
