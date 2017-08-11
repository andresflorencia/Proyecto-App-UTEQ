package com.example.andres_dell.uteqdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.Archivos.Archivos;
import com.example.andres_dell.uteqdemo.Archivos.MainArchivo;
import com.example.andres_dell.uteqdemo.Noticias.AdaptadorNoticias;
import com.example.andres_dell.uteqdemo.Noticias.NoticiaAdapater;
import com.example.andres_dell.uteqdemo.Noticias.Noticias;
import com.example.andres_dell.uteqdemo.Noticias.verNoticia;
import com.example.andres_dell.uteqdemo.Universidad.MainUniversidad;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /////////////////////////////////////
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    /////////////////////////////////////
    String idTipo="";

    ///////Declaración de objeto de clase Validacion//////
    Validaciones objValidaciones=new Validaciones();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*/////////////////////////////////
        recyclerView=(RecyclerView)findViewById(R.id.rvListNoticias);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        /////////////////////////////////*/

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //uso del metodo de verificacion de conexion a internet
        if (!objValidaciones.verificaConexion(this)) {
            Toast.makeText(getBaseContext(),
                    "Comprueba tu conexión a Internet", Toast.LENGTH_LONG)
                    .show();
            //this.finish();
            // fin de "uso del metodo de verificacion de conexion a internet"
        }
        else{
            /*Map<String, String> params = new HashMap<String,String>();

            WebService ws= new WebService("http://186.46.90.102/appUTEQ/webservices/wsNews.php",
                    params, MainActivity.this,
                    MainActivity.this);
            ws.execute("");*/

            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new FragmentoNoticias()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.nav_noticias) {
            Bundle b = new Bundle();
            b.putString("idTipoNoticia","Noticias");
            FragmentoNoticias fragmentoNoticias=new FragmentoNoticias();
            fragmentoNoticias.setArguments(b);
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, fragmentoNoticias).commit();
            //Intent intent = new Intent(MainActivity.this, MainUniversidad.class);
            //startActivity(intent);
        }else if (id == R.id.nav_universidad) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new Fragmento_Universidad()).commit();
            //Intent intent = new Intent(MainActivity.this, MainUniversidad.class);
            //startActivity(intent);
        } else if (id == R.id.nav_investigacion) {
            Bundle b = new Bundle();
            b.putString("idTipoNoticia","Investigacion");
            FragmentoNoticias fragmentoNoticias=new FragmentoNoticias();
            fragmentoNoticias.setArguments(b);
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, fragmentoNoticias).commit();
        }  else if (id == R.id.nav_vinculacion) {
            Bundle b = new Bundle();
            b.putString("idTipoNoticia","Vinculacion");
            FragmentoNoticias fragmentoNoticias=new FragmentoNoticias();
            fragmentoNoticias.setArguments(b);
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, fragmentoNoticias).commit();
        } else if (id == R.id.nav_ofertaacademica) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new Fragmento_OfertaAcademica()).commit();
        } else if (id == R.id.nav_prodeuteq) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new Fragmento_Prodeuteq()).commit();
        } else if (id == R.id.nav_transparencia) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new Fragmento_Transparencia()).commit();
        } else if (id == R.id.nav_rendicioncuentas) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new Fragmento_RendicionCuentas()).commit();
        } else if (id == R.id.nav_archivos) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new Fragmento_Archivos()).commit();
            //Intent intent = new Intent(MainActivity.this, MainArchivo.class);
            //startActivity(intent);
        }else if (id == R.id.nav_consultanotas) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new ConsultaNotasSicau()).commit();
        //Intent intent = new Intent(MainActivity.this, ConsultaNotas.class);
        //startActivity(intent);
        }else if (id == R.id.nav_videosuteq) {
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new CanalYotube()).commit();
            //Intent intent = new Intent(MainActivity.this, VideosUteq.class);
            //startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
   @Override
    public void processFinish(String result) throws JSONException {

        JSONArray objdataarray= new JSONArray (result);
        ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

       // AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);

        NoticiaAdapater noticiaAdapater=new NoticiaAdapater(this,noticias);
        recyclerView.setAdapter(noticiaAdapater);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }*/
}
