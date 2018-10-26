package com.nicolas.vwarwj2.volkswagenagenda.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicolas.vwarwj2.volkswagenagenda.ProfileActivity;
import com.nicolas.vwarwj2.volkswagenagenda.R;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.ContactoModel;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder> {
    ArrayList <ContactoModel> listaContactos;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

                TextView nombre,info;
                ImageView foto;
                LinearLayout idContact;

        public MyViewHolder(View v) {
                    super(v);
            nombre = itemView.findViewById(R.id.idNombre);
            info = itemView.findViewById(R.id.idInfo);
            foto = itemView.findViewById(R.id.idImagen);
            idContact = v.findViewById(R.id.contactoID);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRecyclerView(ArrayList <ContactoModel>  lista) {
        this.listaContactos = lista;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contactos_list, parent, false);
        return new MyViewHolder(layoutView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nombre.setText(listaContactos.get(position).getNombre());
        holder.info.setText(listaContactos.get(position).getInfo());
        holder.foto.setImageResource(listaContactos.get(position).getFoto());
        holder.idContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                           Intent i = new Intent (view.getContext(),ProfileActivity.class);
                i.putExtra("nombre", listaContactos.get(position).getNombre());
                i.putExtra("email",listaContactos.get(position).getInfo());
                i.putExtra("telefono",listaContactos.get(position).getTelefono());
                i.putExtra("imageId",listaContactos.get(position).getFoto());
                view.getContext().startActivity(i);
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return listaContactos.size();
    }

public void setFilter (ArrayList<ContactoModel> nuevaLista) {
        listaContactos = new ArrayList<>();
        listaContactos.addAll(nuevaLista);
        notifyDataSetChanged();
}
}