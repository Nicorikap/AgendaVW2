package com.nicolas.vwarwj2.volkswagenagenda.firebaseServices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.nicolas.vwarwj2.volkswagenagenda.ProfileActivity;
import com.nicolas.vwarwj2.volkswagenagenda.R;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.ContactoModel;


public class FirebaseList extends Fragment {

    RecyclerView listaUsuarios;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_firebaselist);
        //agregarToolbar();
        //init();
        //getUserList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_grid_fragment, container, false);
        listaUsuarios = view.findViewById(R.id.listRecyclerView);
        init();
        getUserList();
        return view;
    }

    private void init() {
        listaUsuarios.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = FirebaseFirestore.getInstance();
    }

    private void getUserList() {
        Query query = db.collection("usuarios").orderBy("nombre");

        FirestoreRecyclerOptions<ContactoModel> contacto = new FirestoreRecyclerOptions.Builder<ContactoModel>()
                .setQuery(query, ContactoModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ContactoModel, UserHolder>(contacto) {
            @Override
            public void onBindViewHolder(UserHolder holder, int position,final ContactoModel model) {

                holder.nombre.setText(model.getNombre());
                holder.info.setText(model.getInfo());
                holder.foto.setImageResource(R.drawable.header);
                holder.idContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), ProfileActivity.class);
                        i.putExtra("nombre", model.getNombre());
                        i.putExtra("email", model.getInfo());
                        i.putExtra("telefono",model.getTelefono());
                        i.putExtra("imageId", R.drawable.header);
                        view.getContext().startActivity(i);
                    }
                });
            }
            @Override
            public UserHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.fragment_contactos_list, group, false);

                return new UserHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };
        adapter.notifyDataSetChanged();
        listaUsuarios.setAdapter(adapter);
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView nombre,info;
        ImageView foto;
        LinearLayout idContact;

        public UserHolder(View v) {
            super(v);
            nombre = itemView.findViewById(R.id.idNombre);
            info = itemView.findViewById(R.id.idInfo);
            foto = itemView.findViewById(R.id.idImagen);
            idContact = v.findViewById(R.id.contactoID);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    /*
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }*/
}
