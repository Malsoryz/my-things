package com.eternity.bystro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        BystroDatabase bystrodb = new BystroDatabase(this);
        Cursor cursor = bystrodb.getTableValueforCart();
        ArrayList<View> cartItems = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            LinearLayout listcart = findViewById(R.id.cartlist);
            do {
                try {
                    int cartid = cursor.getInt(cursor.getColumnIndexOrThrow("cartid"));
                    int productid = cursor.getInt(cursor.getColumnIndexOrThrow("productid"));
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String types = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                    String prices = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                    int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));

                    View listitem = getLayoutInflater().inflate(R.layout.cartitem,listcart,false);

                    ImageView photoframe = listitem.findViewById(R.id.photoframe);
                    TextView productname = listitem.findViewById(R.id.productname);
                    TextView type = listitem.findViewById(R.id.type);
                    TextView price = listitem.findViewById(R.id.price);
                    TextView quanty = listitem.findViewById(R.id.quantity);

                    photoframe.setImageResource(image);
                    productname.setText(name);
                    type.setText(types);
                    price.setText(prices);
                    quanty.setText(String.valueOf(quantity));

                    listitem.setTag(new CartItem(cartid, image, name, productid, types, prices, quantity));

                    listcart.addView(listitem);
                    cartItems.add(listitem);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });
        TextView edit = findViewById(R.id.editcart);
        edit.setOnClickListener(view -> {
            LinearLayout totallayout = findViewById(R.id.totalall);
            MaterialButton checkout = findViewById(R.id.checkout);
            MaterialButton delete = findViewById(R.id.delete);
            if (totallayout.getVisibility() == View.VISIBLE && checkout.getVisibility() == View.VISIBLE && delete.getVisibility() == View.GONE) {
                totallayout.setVisibility(View.GONE);
                checkout.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
                edit.setText("Done");
            } else {
                totallayout.setVisibility(View.VISIBLE);
                checkout.setVisibility(View.VISIBLE);
                delete.setVisibility(View.GONE);
                edit.setText("Edit");
            }
        });
        MaterialButton checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(View -> {
            ArrayList<CartItem> selectedItems = new ArrayList<>();
            for (View itemView : cartItems) {
                CheckBox itemselect = itemView.findViewById(R.id.itemselect);
                if (itemselect.isChecked()) {
                    CartItem item = (CartItem) itemView.getTag();
                    selectedItems.add(item);
                }
            }
            ArrayList<HashMap<String, String>> selectedItemsData = new ArrayList<>();
            for (CartItem item : selectedItems) {
                HashMap<String, String> data = new HashMap<>();
                data.put("cartid", String.valueOf(item.getCartid()));
                data.put("image", String.valueOf(item.getImage()));
                data.put("name", item.getName());
                data.put("productid", String.valueOf(item.getProductid()));
                data.put("types", item.getTypes());
                data.put("prices", item.getPrices());
                data.put("quantity", String.valueOf(item.getQuantity()));
                selectedItemsData.add(data);
            }

            Intent intent = new Intent(ViewCart.this,ViewCheckout.class);
            intent.putExtra("selecteditem",selectedItemsData);
            startActivity(intent);
        });
    }
}