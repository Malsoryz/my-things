package com.eternity.bystro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        BystroDatabase bystrodb = new BystroDatabase(getContext());

        LinearLayout resetdatabase = rootview.findViewById(R.id.resetdatabase);
        resetdatabase.setOnClickListener(view -> {
            bystrodb.recreateTable("product_list",BystroDatabase.CREATE_PRODUCT_LIST_TABLE);
            bystrodb.recreateTable("address",BystroDatabase.CREATE_ADDRESS_TABLE);
            bystrodb.recreateTable("orders",BystroDatabase.CREATE_ORDER_TABLE);
        });

        return rootview;
    }
}