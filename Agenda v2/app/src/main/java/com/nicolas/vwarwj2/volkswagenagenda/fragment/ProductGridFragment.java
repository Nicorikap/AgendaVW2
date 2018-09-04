package com.nicolas.vwarwj2.volkswagenagenda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import com.nicolas.vwarwj2.volkswagenagenda.R;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.SimpleModel;
import com.nicolas.vwarwj2.volkswagenagenda.adapter.AdapterRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductGridFragment extends Fragment{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.product_grid_fragment, container, false);
        setUpToolbar(view);

        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        AdapterRecyclerView adapter = new AdapterRecyclerView(generateSimpleList());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.toolbar, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    private List<SimpleModel> generateSimpleList() {
        List<SimpleModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            simpleViewModelList.add(new SimpleModel(String.format(Locale.US, "This is item %d", i)));
        }

        return simpleViewModelList;
    }
}

