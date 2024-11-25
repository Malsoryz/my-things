package com.eternity.bystro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        Cursor cursor = bystrodb.getTableValuesForCart();

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
                    int preprice = Integer.parseInt(prices);
                    price.setText(MainActivity.formatIntToRP(preprice));
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
        LinearLayout totallayout = findViewById(R.id.totalall);
        MaterialButton checkout = findViewById(R.id.checkout);
        MaterialButton delete = findViewById(R.id.delete);
        edit.setOnClickListener(view -> {
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

        final int[] totalprices = {0};
        final int[] totalitems = {0};

        TextView itemquantity = findViewById(R.id.itemquantity);
        TextView totalprice = findViewById(R.id.totalprice);

        for (View itemView : cartItems) {
            CheckBox itemselect = itemView.findViewById(R.id.itemselect);
            itemselect.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                CartItem item = (CartItem) itemView.getTag();
                int perprice = Integer.parseInt(item.getPrices());
                int peritem = item.getQuantity();
                if (isChecked) {
                    totalprices[0] += perprice * peritem;
                    totalitems[0] += peritem;
                } else {
                    totalprices[0] -= perprice * peritem;
                    totalitems[0] -= peritem;
                }
                itemquantity.setText("Items : " + totalitems[0]);
                totalprice.setText("Total : " + MainActivity.formatIntToRP(totalprices[0]));
            });
        }
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
                data.put("image", String.valueOf(item.getImage()));
                data.put("name", item.getName());
                data.put("productid", String.valueOf(item.getProductid()));
                data.put("types", item.getTypes());
                data.put("prices", item.getPrices());
                data.put("quantity", String.valueOf(item.getQuantity()));
                selectedItemsData.add(data);
            }
            if (!selectedItems.isEmpty()) {
                Intent intent = new Intent(ViewCart.this,ViewCheckout.class);
                intent.putExtra("selecteditem",selectedItemsData);
                intent.putExtra("totalprices", totalprices[0]);
                intent.putExtra("totalitems", totalitems[0]);
                startActivity(intent);
            } else {
                Toast.makeText(this,"Select the product first",Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(view -> {
            ArrayList<View> itemsToRemove = new ArrayList<>();
            ArrayList<CartItem> itemsToDelete = new ArrayList<>();

            for (View itemView : cartItems) {
                CheckBox itemselect = itemView.findViewById(R.id.itemselect);
                if (itemselect.isChecked()) {
                    CartItem item = (CartItem) itemView.getTag();
                    int cartid = item.getCartid();

                    int perprice = Integer.parseInt(item.getPrices());
                    int peritem = item.getQuantity();

                    totalprices[0] -= perprice * peritem;
                    totalitems[0] -= peritem;

                    itemsToRemove.add(itemView);
                    itemsToDelete.add(item);
                }
            }
            LinearLayout listcart = findViewById(R.id.cartlist);
            for (View itemView : itemsToRemove) {
                listcart.removeView(itemView);
                cartItems.remove(itemView);
            }
            for (CartItem item : itemsToDelete) {
                bystrodb.deleteCartItem(item.getCartid());
            }
            itemquantity.setText("Items : " + totalitems[0]);
            totalprice.setText("Total : " + MainActivity.formatIntToRP(totalprices[0]));

            if (itemsToRemove.isEmpty()) {
                Toast.makeText(this, "No items selected for deletion", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Items removed from cart", Toast.LENGTH_SHORT).show();
            }
        });

    }
}