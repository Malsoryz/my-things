package com.eternity.bystro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    public OrdersFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_orders, container, false);

        LinearLayout listorder = rootview.findViewById(R.id.listorder);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            ArrayList<ProductData> dataproduct = mainActivity.getDatabystro();

            for (ProductData itemdata : dataproduct){
                String productname = itemdata.getProductname();
                float priced = itemdata.getPrice();
                String type = itemdata.getType();
                String desc = itemdata.getDesc();
                int photolink = itemdata.getPhotolink();

                View listitem = getLayoutInflater().inflate(R.layout.orderitem, listorder, false);
                ImageView photoframe = listitem.findViewById(R.id.photoframe);
                TextView name = listitem.findViewById(R.id.productname);
                TextView typed = listitem.findViewById(R.id.type);
                TextView price = listitem.findViewById(R.id.price);
                TextView quantity = listitem.findViewById(R.id.quantity);
                TextView status = listitem.findViewById(R.id.status);

                photoframe.setImageResource(photolink);
                photoframe.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                    int width = photoframe.getWidth();
                    photoframe.getLayoutParams().height = width;
                    photoframe.requestLayout();
                });
                name.setText(productname);
                typed.setText(type);
                String parsefloat = String.valueOf((int) priced);
                String rupiah = "Rp" + String.format("%,d", Integer.parseInt(parsefloat)).replace(",", ".");
                price.setText(rupiah);
                quantity.setText("1");
                status.setText("on the way");

                listorder.addView(listitem);
            }
        }
        return rootview;
    }
}