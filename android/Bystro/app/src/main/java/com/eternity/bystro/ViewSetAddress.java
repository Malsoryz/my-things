package com.eternity.bystro;

import static java.security.AccessController.getContext;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class ViewSetAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_set_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BystroDatabase bystrodb = new BystroDatabase(this);
        Cursor cursor = bystrodb.getTableValues("address");

        //show registered address
        LinearLayout addressframe = findViewById(R.id.addressframe);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int addressid = cursor.getInt(cursor.getColumnIndexOrThrow("addressid"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                String useraddress = cursor.getString(cursor.getColumnIndexOrThrow("useraddress"));

                View addressitem = getLayoutInflater().inflate(R.layout.addressitem,addressframe,false);
                TextView addressusername = addressitem.findViewById(R.id.addressusername);
                TextView addresses = addressitem.findViewById(R.id.addresses);

                addressusername.setText(username);
                addresses.setText(useraddress);
                addressframe.addView(addressitem);
            } while (cursor.moveToNext());
            cursor.close();
        }

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });

        //dialog add new address
        MaterialButton addnewaddress = findViewById(R.id.addnewaddress);
        addnewaddress.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View addaddress = getLayoutInflater().inflate(R.layout.add_new_address_dialog,null);
            builder.setView(addaddress);
            AlertDialog dialog = builder.create();

            EditText sendernameinput = addaddress.findViewById(R.id.sendernameinput);
            EditText addressinput = addaddress.findViewById(R.id.addressinput);
            ImageButton close = addaddress.findViewById(R.id.back);
            MaterialButton addaddressbtn = addaddress.findViewById(R.id.addaddress);

            close.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            addaddressbtn.setOnClickListener(view2 -> {
                String getsendername = sendernameinput.getText().toString();
                String getaddress = addressinput.getText().toString();
                if (!getsendername.isEmpty() && !getaddress.isEmpty()) {
                    bystrodb.addNewAddress(getsendername,getaddress);
                    Toast.makeText(this,"Address added!",Toast.LENGTH_SHORT).show();
                    recreate();
                    dialog.dismiss();
                }
            });

            dialog.show();
        });
    }
}