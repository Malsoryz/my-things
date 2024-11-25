package com.eternity.bystro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class ViewProduct extends AppCompatActivity {

    private BystroDatabase bystrodb;

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

        bystrodb = new BystroDatabase(this);

        Intent getintent = getIntent();
        int productid = getintent.getIntExtra("productid",0);
        String productname = getintent.getStringExtra("name");
        String type = getintent.getStringExtra("type");
        String price = getintent.getStringExtra("price");
        int image = getintent.getIntExtra("image",0);
        String desc = getintent.getStringExtra("desc");
        int stock = getintent.getIntExtra("stock",0);

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

        String getquantity = howmuch.getText().toString();
        int quanty = Integer.parseInt(getquantity);

        //set btn
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });
        ImageButton tocart = findViewById(R.id.tocart);
        tocart.setOnClickListener(view -> {
            Intent intent = new Intent(ViewProduct.this,ViewCart.class);
            startActivity(intent);
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
            if (quantity < stock) {
                quantity += 1;
                howmuch.setText(String.valueOf((int) quantity));
            }
        });
        photoframe.setImageResource(image);
        photoframe.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int width = photoframe.getWidth();
            photoframe.getLayoutParams().height = width;
            photoframe.requestLayout();
        });
        productview.setText(productname);
        typeview.setText(type);
        int preprice = Integer.parseInt(price);
        priceview.setText(MainActivity.formatIntToRP(preprice));
        stockview.setText("Stock " + stock);
        descview.setText(desc);

        addtocart.setOnClickListener(view -> {
            if (quanty > 0) {
                try {
                    if (bystrodb.checkProductInCart(productid)) {
                        updateCartQuantity(productid,quanty);
                    } else {
                        String[][] cartlist = {{String.valueOf(productid), String.valueOf(quanty)}};
                        addToCart(cartlist);
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Order failed to add to cart", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Cannot be added if quantity is 0", Toast.LENGTH_SHORT).show();
            }
        });

        checkout.setOnClickListener(view -> {
            int perprice = Integer.parseInt(price);
            int totalprice = perprice * quanty;

            ArrayList<HashMap<String, String>> selectedItemsData = new ArrayList<>();
            HashMap<String, String> data = new HashMap<>();
            data.put("image", String.valueOf(image));
            data.put("name", productname);
            data.put("productid", String.valueOf(productid));
            data.put("types", type);
            data.put("prices", price);
            data.put("quantity", String.valueOf(quanty));
            selectedItemsData.add(data);

            Intent intent = new Intent(this,ViewCheckout.class);
            intent.putExtra("selecteditem",selectedItemsData);
            intent.putExtra("totalprices",totalprice);
            intent.putExtra("totalitems",quanty);
            startActivity(intent);
        });
    }
    private void updateCartQuantity(int productid, int quanty) {
        try {
            bystrodb.updateCartQuantity(productid, quanty);
            Toast.makeText(this, "Cart updated", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error updating cart", Toast.LENGTH_SHORT).show();
        }
    }
    private void addToCart(String[][] cartlist) {
        try {
            bystrodb.addToCart(cartlist);
            Toast.makeText(this, "Order added to cart", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error adding to cart", Toast.LENGTH_SHORT).show();
        }
    }
}