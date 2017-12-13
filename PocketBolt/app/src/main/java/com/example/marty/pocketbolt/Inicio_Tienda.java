package com.example.marty.pocketbolt;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio_Tienda extends Fragment {

    View view;
    DBaccess databaseAccess;
    ArrayList<String> categorias;
    ExpandableListView expandibleList;

    public Inicio_Tienda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio__tienda, container, false);

        expandibleList = view.findViewById(R.id.Categorias);

        //Carrusel de avisos
        DiscreteScrollView scrollView = view.findViewById(R.id.banner);
        ArrayList<Bitmap> data = new ArrayList<>();
        data.add(BitmapFactory.decodeResource(getResources(), R.drawable.welcome_shop));
        data.add(BitmapFactory.decodeResource(getResources(), R.drawable.trigo_shop));
        scrollView.setAdapter(new banner_adapter(data));

        //Populate scrollView with categories
        setCategorias();
        populateScrollView();

        setClicks();

        return view;
    }

    public void setClicks(){
        //Buscar click
        ((ImageView)view.findViewById(R.id.searchIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Buscando...", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        expandibleList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                TextView itemName = (TextView)v.findViewById(R.id.itemName);
                TextView itemCost = (TextView)v.findViewById(R.id.itemPrice);

                String headerInfo = categorias.get(groupPosition);
                databaseAccess = DBaccess.getInstance(view.getContext());
                databaseAccess.open();
                String detailInfo =  databaseAccess.getGroupItems(headerInfo).get(childPosition);
                String cost = databaseAccess.getItemPrice(detailInfo);
                databaseAccess.close();

                itemName.setText(detailInfo);
                itemCost.setText(cost);
                return false;
            }
        });
        */
    }
    public void setCategorias(){
        databaseAccess = DBaccess.getInstance(view.getContext());
        databaseAccess.open();
        categorias = databaseAccess.getItemGroups();
        databaseAccess.close();
    }

    public void populateScrollView(){
        HashMap<String, ArrayList<String>> expandableListDetail = new HashMap<>();

        databaseAccess = DBaccess.getInstance(view.getContext());
        databaseAccess.open();

        for (String cat : categorias)
        {
            ArrayList<String> items = new ArrayList<>();
            items =  databaseAccess.getGroupItems(cat);
            expandableListDetail.put(cat, items);
        }
        databaseAccess.close();
        expandibleList.setAdapter(new expandiblelist_adapter(this.getContext(), categorias, expandableListDetail));
    }

}
