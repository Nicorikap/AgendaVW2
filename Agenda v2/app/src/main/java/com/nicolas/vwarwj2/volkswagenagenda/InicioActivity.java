package com.nicolas.vwarwj2.volkswagenagenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.nicolas.vwarwj2.volkswagenagenda.firebaseServices.FirebaseList;
import com.nicolas.vwarwj2.volkswagenagenda.fragment.ContactosFragment;

public class InicioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragmentoPrincipal();

        final ImageButton botonImagenHeader = (ImageButton) navigationView.getHeaderView(0).findViewById(R.id.imagenPerfil);

        botonImagenHeader.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (navigationView.getContext(),ProfileActivity.class);
                addingProfileInfo(i);
                startActivity(i);
            }
        });

    }

    public void onResume (){
        navigationView.getMenu().getItem(0).setChecked(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Cerrando Aplicacion")
                    .setMessage("¿Estas seguro de salir?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(1);
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.mycart) {
         //   return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragmentOption = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (id) {
            case R.id.item_inicio:
            //fragmentOption = new ContactosFragment(); Aca estoy reiniciando el fragmento en vez de usar el creado por default al inicio.
                item.setChecked(true);
                goingBackToHome();
            break;
            case R.id.item_cuenta:
                item.setChecked(true);
                Intent i = new Intent (this,ProfileActivity.class);
                addingProfileInfo(i);
                startActivity(i);

                break;
            case R.id.item_configuracion:
                item.setChecked(true);
                iniciarIntento(SettingsActivity.class);
                break;
            case R.id.item_agregarUsuario:
                item.setChecked(true);
                iniciarIntento(FirebaseExample.class);
                break;
            case R.id.item_listaFirebase:
                item.setChecked(true);
                //iniciarIntento(FirebaseList.class);
                break;
        }

        if (fragmentOption != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentOption)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setFragmentoPrincipal(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.contenedor_principal, new FirebaseList())
                .commit();

    }

    public void iniciarIntento(Class c){
        Intent i = new Intent (this,c);
        startActivity(i);
    }

    public void goingBackToHome(){
        int pos=0;
        FirebaseList fragment = (FirebaseList) getSupportFragmentManager().getFragments().get(pos);
    }

    public void addingProfileInfo (Intent i){
        i.putExtra("nombre", "Nicolas Rikap");
        i.putExtra("email","nicolasrikap@outlook.com");
        i.putExtra("telefono",1160334291);
        i.putExtra("imageId",R.drawable.paisaje);
        i.putExtra("MenuPerfil",true);
    }
}