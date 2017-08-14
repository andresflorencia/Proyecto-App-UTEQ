package com.example.andres_dell.uteqdemo.Noticias;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Fade;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Constante;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Validaciones;
import com.example.andres_dell.uteqdemo.Fragmento_Dialogo;
import com.example.andres_dell.uteqdemo.R;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class verNoticia extends AppCompatActivity implements Asynchtask {

    Constante objConstante=new Constante();
    Validaciones objValidaciones=new Validaciones();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fade fade=new Fade(Fade.IN);
        fade.setDuration(1000);
        fade.setInterpolator(new DecelerateInterpolator());
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.activity_ver_noticia);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Noticia");

        Bundle bundle = this.getIntent().getExtras();

        /*TextView txttituloNoticia = (TextView)findViewById(R.id.txtTitulo);
        txttituloNoticia.setText(bundle.getString("Titulo"));*/
        String ip=objValidaciones.ipAConetarse(this);
        String idNoticia=bundle.getString("idNoticia");
        Map<String, String> params = new HashMap<String, String>();
       // params.put("asunto","NtcPrD");
        //params.put("idnoticia",bundle.getString("idNoticia"));
        WebService ws= new WebService("http://"+ip+objConstante.getWsNoticiaPorId()+idNoticia
                , params, verNoticia.this, verNoticia.this);
        ws.execute("");

    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONArray objdataarray= new JSONArray (result);
        //ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

        JSONObject jsonObj= objdataarray.getJSONObject(0);

        //Extrayendo datos del JSON e insertando en cada campo
        TextView txttituloNoticia = (TextView)findViewById(R.id.txtTitulo);
        txttituloNoticia.setText(Html.fromHtml(jsonObj.getString("titulo")));

        TextView txtFechaCategoria = (TextView)findViewById(R.id.txtFechaCategoria);
        //txtFechaCategoria.setText(jsonObj.getString("publicacion").substring(0,10).concat(" | ").concat(jsonObj.getString("categoria")));
        txtFechaCategoria.setText(jsonObj.getString("publicacion").substring(0,10).concat(" | "));
        TextView txtIntroduccion = (TextView)findViewById(R.id.txtIntroduccion);
        txtIntroduccion.setText(Html.fromHtml(jsonObj.getString("intro")));

        TextView txtContenido = (TextView)findViewById(R.id.txtcontenido);
        txtContenido.setText(Html.fromHtml(jsonObj.getString("texto")));

        //cargar la imagen para cada noticia
        String urlUteq=objConstante.getUrlUteq();
        String idNoti=jsonObj.getString("url");
        ImageView imgNoticia=(ImageView)findViewById(R.id.imgNoti);
        Glide.with(this)
                .load(urlUteq.concat(idNoti))
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(imgNoticia);

        Bundle b = new Bundle();
        b.putString("url",urlUteq.concat(idNoti));
        b.putString("titulo",jsonObj.getString("titulo"));

        final DialogFragment dialogFragment= new Fragmento_Dialogo();
        dialogFragment.setArguments(b);


        imgNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getSupportFragmentManager(), "Fragmento_Dialogo");
            }
        });
    }
}
