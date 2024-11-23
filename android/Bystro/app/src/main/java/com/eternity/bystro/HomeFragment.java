package com.eternity.bystro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        GridLayout listorder = rootview.findViewById(R.id.listorder);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            ArrayList<ProductData> dataproduct = mainActivity.getDatabystro();

            for (ProductData itemdata : dataproduct){
                String productname = itemdata.getProductname();
                float priced = itemdata.getPrice();
                String type = itemdata.getType();
                String desc = itemdata.getDesc();
                int photolink = itemdata.getPhotolink();

                View griditem = getLayoutInflater().inflate(R.layout.homeitem, listorder, false);
                MaterialCardView cardview = griditem.findViewById(R.id.cardview);
                ImageView photoframe = griditem.findViewById(R.id.photoframe);
                TextView name = griditem.findViewById(R.id.homeproductname);
                TextView price = griditem.findViewById(R.id.homeproductprice);

                cardview.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(), ViewProduct.class);

                    intent.putExtra("productname",productname);
                    intent.putExtra("price",priced);
                    intent.putExtra("type",type);
                    intent.putExtra("desc",desc);
                    intent.putExtra("photolink",photolink);

                    startActivity(intent);
                });
                photoframe.setImageResource(photolink);
                name.setText(productname);
                String parsefloat = String.valueOf((int) priced);
                String rupiah = "Rp" + String.format("%,d", Integer.parseInt(parsefloat)).replace(",", ".");
                price.setText(rupiah);

                listorder.addView(griditem);
            }
        }

        return rootview;
    }
}
