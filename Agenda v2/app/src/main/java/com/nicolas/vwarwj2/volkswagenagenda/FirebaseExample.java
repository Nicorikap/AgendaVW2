package com.nicolas.vwarwj2.volkswagenagenda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseExample extends AppCompatActivity{

    private static final String TAG = "FirebaseExample";

        private static final String KEY_NAME ="nombre";
        private static final String KEY_MAIL ="email";
        private static final String KEY_TEL ="telefono";

    private EditText texto_nombre;
    private EditText texto_mail;
    private EditText texto_tel;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_firebase);
        agregarToolbar();
        texto_nombre = findViewById(R.id.firebase_nombre);
        texto_mail = findViewById(R.id.firebase_mail);
        texto_tel = findViewById(R.id.firebase_tel);
    }

    public void enviarUsuario (View v) {
        String nombre = texto_nombre.getText().toString();
        String mail = texto_mail.getText().toString();
        String tel =texto_tel.getText().toString();
        int telefono = Integer.parseInt(tel);

        Map<String,Object> user = new HashMap<>();
        user.put(KEY_NAME,nombre);
        user.put(KEY_MAIL,mail);
        user.put(KEY_TEL,telefono);

        db.collection("usuarios").document().set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirebaseExample.this, "Usuario guardado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseExample.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());
                    }
                });
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
}
