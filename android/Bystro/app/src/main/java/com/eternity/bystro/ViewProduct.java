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

        Intent intent = getIntent();
        String productname = intent.getStringExtra("productname");
        float price = intent.getFloatExtra("price",0);
        String type = intent.getStringExtra("type");
        String desc = intent.getStringExtra("desc");
        int photolink = intent.getIntExtra("photolink",0);

        ImageView photoframe = findViewById(R.id.photoframe);
        TextView productview = findViewById(R.id.productname);
        TextView descview = findViewById(R.id.desc);

        photoframe.setImageResource(photolink);
        productview.setText(productname);
        descview.setText(desc);

        photoframe.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int width = photoframe.getWidth();
            photoframe.getLayoutParams().height = width;
            photoframe.requestLayout();
        });

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

        ImageButton less = findViewById(R.id.lessquantity);
        TextView howmuch = findViewById(R.id.howmuchadd);
        ImageButton add = findViewById(R.id.addquantity);

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
    }
}