package com.eternity.bystro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class ViewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BystroDatabase bystrodb = new BystroDatabase(this);

        Intent intent = getIntent();
        int productid = intent.getIntExtra("productid",0);
        String productname = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String price = intent.getStringExtra("price");
        int image = intent.getIntExtra("image",0);
        String desc = intent.getStringExtra("desc");
        int stock = intent.getIntExtra("stock",0);

        ImageView photoframe = findViewById(R.id.photoframe);
        TextView productview = findViewById(R.id.productname);
        TextView typeview = findViewById(R.id.type);
        TextView priceview = findViewById(R.id.price);
        TextView stockview = findViewById(R.id.stock);
        TextView descview = findViewById(R.id.desc);
        ImageButton less = findViewById(R.id.lessquantity);
        TextView howmuch = findViewById(R.id.howmuchadd);
        ImageButton add = findViewById(R.id.addquantity);
        MaterialButton addtocart = findViewById(R.id.addtocart);
        MaterialButton checkout = findViewById(R.id.checkout);

        //set btn
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });
        ImageButton tocart = findViewById(R.id.tocart);
        tocart.setOnClickListener(view -> {
            Intent anointent = new Intent(ViewProduct.this,ViewCart.class);
            startActivity(anointent);
        });

        less.setOnClickListener(view -> {
            String much = howmuch.getText().toString();
            float quantity = Float.parseFloat(much);
            if (quantity > 0) {
                quantity -= 1;
                howmuch.setText(String.valueOf((int) quantity));
            }
        });

        add.setOnClickListener(view -> {
            String much = howmuch.getText().toString();
            float quantity = Float.parseFloat(much);
            quantity += 1;
            howmuch.setText(String.valueOf((int) quantity));
        });
        photoframe.setImageResource(image);
        photoframe.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int width = photoframe.getWidth();
            photoframe.getLayoutParams().height = width;
            photoframe.requestLayout();
        });
        productview.setText(productname);
        typeview.setText(type);
        priceview.setText(price);
        stockview.setText("Stock " + stock);
        descview.setText(desc);

        addtocart.setOnClickListener(view -> {
            String getquantity = howmuch.getText().toString();
            int quanty = Integer.parseInt(getquantity);

            String[][] cartlist = {
                    {String.valueOf(productid), String.valueOf(quanty)}
            };
            bystrodb.addtocart(cartlist);
        });
    }
}