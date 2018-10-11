package com.nicolas.vwarwj2.volkswagenagenda;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    boolean valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scroll);
        Intent i = getIntent();
        String nombre = i.getStringExtra("nombre");
        String mail= i.getStringExtra("email");
        TextView textMail = (TextView) findViewById(R.id.tvNumber3);       // EMAIL
            textMail.setText(mail);
        int telefono = i.getIntExtra("telefono",0);
       TextView textTel = (TextView) findViewById(R.id.tvNumber1);       // TELEFONO
       TextView textTel1 = (TextView) findViewById(R.id.tvNumber2);
            textTel.setText(String.valueOf(telefono));
            textTel1.setText(String.valueOf(telefono));
        int idFoto = i.getIntExtra("imageId",0);
        ImageView imagePhoto = (ImageView) findViewById(R.id.fotoID);
           imagePhoto.setImageResource(idFoto);
        valor = i.getBooleanExtra("MenuPerfil",false);


        final RelativeLayout botonWs = (RelativeLayout) findViewById(R.id.wsOption);
        final RelativeLayout botonMail = (RelativeLayout) findViewById(R.id.emailOption);
        final RelativeLayout botonLlamar = (RelativeLayout) findViewById(R.id.callingOption);

        llamarPersona(botonLlamar,telefono);
        enviarMail(botonMail,mail);
        enviarWhatsapp(botonWs,telefono);
        agregarToolbar();
        definirCollapsingToolbar(nombre);
        definirBotonMail(mail);
    }

    @Override
        protected void onNewIntent (Intent intent) {
            setIntent(intent);

        }

    private void enviarWhatsapp(RelativeLayout botonWs,final int telefono) {
        botonWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:"+"+"+549+telefono);
                Intent wsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                wsIntent.setPackage("com.whatsapp");
                tryButton(wsIntent);
            }
        });
    }
    public void llamarPersona(RelativeLayout boton,final int numero) {
        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+numero));
                tryButton(callIntent);
            }
        });
    }
    public void enviarMail(RelativeLayout boton, final String email){
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",email,null));
                tryButton(emailIntent);
            }
        });
    }

    public void tryButton(Intent botonIntent) {
        try {
            startActivity(botonIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No se puede iniciar",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(valor) {
            getMenuInflater().inflate(R.menu.menu_profile, menu);
        }
        else {
            getMenuInflater().inflate(R.menu.navigation_menu, menu);
        }
        return true;

    }
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);

        }
    }
    private void definirCollapsingToolbar(String nombre) {
        Context context = this;
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context,R.color.colorAccent));
        collapsingToolbarLayout.setTitle(nombre);
    }
    private void definirBotonMail(final String mail) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",mail,null));
                tryButton(mailIntent);
            }
        });
    }
}
