package com.nicolas.vwarwj2.volkswagenagenda.pojo;

import java.util.Comparator;

public class ContactoModel {
    private String nombre;
    private String info;
    private int foto;
    private int tel;

    public ContactoModel (){

    }

    public ContactoModel(String nombre, String info, int foto, int telefono) {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
        this.tel = telefono;
    }
    public static final Comparator<ContactoModel> BY_NAME_ALPHABETICAL = new Comparator<ContactoModel>() {
        @Override
        public int compare(ContactoModel contactoModel, ContactoModel t1) {
            return contactoModel.getNombre().compareTo(t1.getNombre());
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getTelefono () {return tel;}

    public void setTelefono (int tele) {this.tel = tele;}
}
