package com.example.andres_dell.uteqdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.ClasesComplementarias.Constante;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Validaciones;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, Asynchtask {

    private SliderLayout mDemoSlider;
    public HashMap<String,String> url_maps=new HashMap<>();

    public Bundle bundleSlider=new Bundle();

    /////////////////////////////////////
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    /////////////////////////////////////
    String idTipo="";

    ///////Declaración de objeto de clases Complementarias//////
    Validaciones objValidaciones=new Validaciones();
    Constante objConstante=new Constante();

    public HashMap<String, String> getUrl_maps() {return url_maps;}

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
                    objConstante.getMensajeSinConexion(), Toast.LENGTH_LONG)
                    .show();
            //this.finish();
            // fin de "uso del metodo de verificacion de conexion a internet"
        }
        else{
            Map<String, String> params = new HashMap<String,String>();

            WebService ws= new WebService("http://"+objValidaciones.ipAConetarse(this)+objConstante.getWsSliderByEstado(),
                    params, MainActivity.this,
                    MainActivity.this);
            ws.execute("");



            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new FragmentoNoticias()).commit();
        }
    }

    //////////////MÉTODOS DEL SLIDER//////////
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Aqui se puede poner lo que se quiere abrir o ejecutar al darle clic a un elemento del slider
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    //////////FIN DE MÉTODOS DEL SLIDER////////////

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

        getSupportActionBar().setTitle(item.getTitle());

        if (id == R.id.nav_noticias) {
            Bundle b = new Bundle();
            b.putString("idTipoNoticia","Noticias");
            b.putBundle("bundleSlider",bundleSlider);
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
            b.putBundle("bundleSlider",bundleSlider);
            FragmentoNoticias fragmentoNoticias=new FragmentoNoticias();
            fragmentoNoticias.setArguments(b);
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, fragmentoNoticias).commit();
        }  else if (id == R.id.nav_vinculacion) {
            Bundle b = new Bundle();
            b.putString("idTipoNoticia","Vinculacion");
            b.putBundle("bundleSlider",bundleSlider);
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
            fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, new CanalYoutube()).commit();
            //Intent intent = new Intent(MainActivity.this, VideosUteq.class);
            //startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   @Override
    public void processFinish(String result) throws JSONException {

       mDemoSlider = (SliderLayout) findViewById(R.id.slider);

       JSONArray objdataarray= new JSONArray (result);
       //HashMap<String,String> url_maps=new HashMap<>();
       for (int i = 0; i < objdataarray.length(); i++) {
           bundleSlider.putString(objdataarray.getJSONObject(i).getString("titulo"),objdataarray.getJSONObject(i).getString("img"));
       }

       /////////////SLIDER//////////

            /*url_maps.put("Aqui poner alguna wa", "http://www.uteq.edu.ec/images/slider/slider-01.jpg");
            url_maps.put("Aqui poner otra wa", "http://www.uteq.edu.ec/images/slider/pp_slider-01.jpg");
            url_maps.put("Aqui poner una tercer wa", "http://www.uteq.edu.ec/images/slider/correo-01.jpg");*/


       for(String name : bundleSlider.keySet()){
           TextSliderView textSliderView = new TextSliderView(this);
           // initialize a SliderLayout
           textSliderView
                   .description(name)
                   .image(objConstante.getUrlImgSlider()+bundleSlider.get(name))
                   .setScaleType(BaseSliderView.ScaleType.Fit)
                   .setOnSliderClickListener(MainActivity.this);

           //add your extra information
           textSliderView.bundle(new Bundle());
           textSliderView.getBundle()
                   .putString("extra",name);

           mDemoSlider.addSlider(textSliderView);
       }

       //Efecto de transicion: Aqui solo dejar uno de estos, el que mas guste
       //Como recomendación se podría poner configurable
       mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

       mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
       mDemoSlider.setCustomAnimation(new DescriptionAnimation());
       mDemoSlider.setDuration(6000);
       /////////////////FIN SLIDER//////////////////////////////

    }
}
