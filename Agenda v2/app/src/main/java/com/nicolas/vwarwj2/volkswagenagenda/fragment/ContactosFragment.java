package com.nicolas.vwarwj2.volkswagenagenda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nicolas.vwarwj2.volkswagenagenda.R;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.ContactoModel;
import com.nicolas.vwarwj2.volkswagenagenda.adapter.AdapterRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class ContactosFragment extends Fragment {

    ArrayList<ContactoModel> listaContactos;
    RecyclerView recyclerContactos;
    AdapterRecyclerView adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        listaContactos = new ArrayList<>();
        llenarListaConContactos();
        Collections.sort(listaContactos, ContactoModel.BY_NAME_ALPHABETICAL);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product_grid_fragment, container, false);
        recyclerContactos = view.findViewById(R.id.listRecyclerView);
        recyclerContactos.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter =  new AdapterRecyclerView(listaContactos);
        recyclerContactos.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.toolbar,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s = s.toLowerCase();
                ArrayList<ContactoModel> nuevaLista = new ArrayList<>();
                for (ContactoModel contacto : listaContactos){
                    String name = contacto.getNombre().toLowerCase();
                    if(name.contains(s))
                        nuevaLista.add(contacto);
                }
                adapter.setFilter(nuevaLista);
                return true;
            }
        });
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void llenarListaConContactos() {
        listaContactos.add(new ContactoModel("Nicolas","nicolasrikap@outlook.com",R.drawable.header,1162521895));
        listaContactos.add(new ContactoModel("Melina","melina@outlook.com",R.drawable.header, 1145665444));
        listaContactos.add(new ContactoModel("Adriana","adriana@outlook.com",R.drawable.header,1141429811));
        listaContactos.add(new ContactoModel("Jose","jose@outlook.com",R.drawable.header,1148659878));
        listaContactos.add(new ContactoModel("Adrian","adrian@outlook.com",R.drawable.header,1164983521));
        listaContactos.add(new ContactoModel("Maria","maria@outlook.com",R.drawable.header,1148975848));
        listaContactos.add(new ContactoModel("Pablo","pablo@outlook.com",R.drawable.header,1148796584));
        listaContactos.add(new ContactoModel("Alfredo","alfredo@outlook.com",R.drawable.header,1148963498));
        listaContactos.add(new ContactoModel("Roberto","roberto@outlook.com",R.drawable.header,1149852321));
        listaContactos.add(new ContactoModel("Florencia","florencia@outlook.com",R.drawable.header,1149815631));
        listaContactos.add(new ContactoModel("Ana","ana@outlook.com",R.drawable.header,1164565665));
    }
}