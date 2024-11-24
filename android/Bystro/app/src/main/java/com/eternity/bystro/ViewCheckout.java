package com.eternity.bystro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ViewCheckout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });
        Intent getintent = getIntent();
        int totalprices = getintent.getIntExtra("totalprices",0);
        int totalitems = getintent.getIntExtra("totalitems",0);
        int deliverypay = 31000;
        int totalpay = totalprices + deliverypay;
        ArrayList<HashMap<String, String>> selectedItemsData = (ArrayList<HashMap<String, String>>) getintent.getSerializableExtra("selecteditem");
        if (selectedItemsData != null) {
            for (HashMap<String, String> data : selectedItemsData) {
                int cartid = Integer.parseInt(Objects.requireNonNull(data.get("cartid")));
                int image = Integer.parseInt(Objects.requireNonNull(data.get("image")));
                String name = data.get("name");
                int productid = Integer.parseInt(Objects.requireNonNull(data.get("productid")));
                String types = data.get("types");
                String prices = data.get("prices");
                int quantity = Integer.parseInt(Objects.requireNonNull(data.get("quantity")));

                LinearLayout selecteditem = findViewById(R.id.selecteditem);
                View selected = getLayoutInflater().inflate(R.layout.selecteditem,selecteditem,false);

                ImageView photoframe = selected.findViewById(R.id.photoframe);
                TextView productname = selected.findViewById(R.id.productname);
                TextView type = selected.findViewById(R.id.type);
                TextView quanty = selected.findViewById(R.id.quantity);
                TextView price = selected.findViewById(R.id.price);

                TextView subtotalprices = findViewById(R.id.subtotalprice);
                TextView totalitem = findViewById(R.id.totalitems);
                TextView delivery = findViewById(R.id.subtotaldelivery);
                TextView totalpayment = findViewById(R.id.totalpayment);

                MaterialButton makeorder = findViewById(R.id.makeorder);
                makeorder.setOnClickListener(view -> {
                    Toast.makeText(this,"ordered!",Toast.LENGTH_SHORT).show();
                });

                photoframe.setImageResource(image);
                productname.setText(name);
                type.setText(types);
                price.setText(prices);
                quanty.setText(String.valueOf(quantity));

                subtotalprices.setText(String.valueOf(totalprices));
                totalitem.setText(String.valueOf(totalitems));
                delivery.setText(String.valueOf(deliverypay));
                totalpayment.setText(String.valueOf(totalpay));

                selecteditem.addView(selected);
            }
        }
    }
}