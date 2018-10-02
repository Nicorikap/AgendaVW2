package com.nicolas.vwarwj2.volkswagenagenda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolas.vwarwj2.volkswagenagenda.R;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.ContactoModel;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.SimpleModel;
import com.nicolas.vwarwj2.volkswagenagenda.adapter.AdapterRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductGridFragment extends Fragment{

    ArrayList<ContactoModel> listaContactos;
    RecyclerView recyclerContactos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        listaContactos = new ArrayList<>();

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.product_grid_fragment, container, false);

        recyclerContactos = view.findViewById(R.id.listRecyclerView);
        //recyclerContactos.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

        recyclerContactos.setLayoutManager(new LinearLayoutManager(getActivity()));
        llenarListaConContactos();
        AdapterRecyclerView adapter =  new AdapterRecyclerView(listaContactos);
        recyclerContactos.setAdapter(adapter);
        /*RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        AdapterRecyclerView adapter = new AdapterRecyclerView(generateSimpleList());
        recyclerView.setAdapter(adapter);
*/
        return view;
    }

    private void llenarListaConContactos() {
        listaContactos.add(new ContactoModel("Nicolas","nicolasrikap@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Melina","melina@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Adriana","adriana@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Jose","jose@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Adrian","adrian@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Maria","maria@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Pablo","pablo@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Alfredo","alfredo@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Roberto","roberto@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Florencia","florencia@outlook.com",R.mipmap.ic_launcher));
        listaContactos.add(new ContactoModel("Ana","ana@outlook.com",R.mipmap.ic_launcher));

    }

    private List<SimpleModel> generateSimpleList() {
        List<SimpleModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            simpleViewModelList.add(new SimpleModel(String.format(Locale.US, "Usuario %d", i)));
        }

        return simpleViewModelList;
    }
}

