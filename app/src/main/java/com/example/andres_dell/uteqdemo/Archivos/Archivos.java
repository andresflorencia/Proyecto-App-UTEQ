package com.example.andres_dell.uteqdemo.Archivos;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andres_dell.uteqdemo.ClasesComplementarias.Constante;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Validaciones;
import com.example.andres_dell.uteqdemo.R;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Archivos extends AppCompatActivity implements Asynchtask{

    Validaciones validaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = this.getIntent().getExtras();


        TextView txttituloNoticia = (TextView)findViewById(R.id.txtCategoria);
        txttituloNoticia.setText(bundle.getString("TituloCategoria"));


        String ip= Validaciones.ipAConetarse(this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("idcategoria", bundle.get("idCategoria").toString());
        WebService ws= new WebService("http://"+ip+"/appUTEQ/ws/info.php", params, Archivos.this, Archivos.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONArray objdataarray= new JSONArray (result);
        ArrayList<Categorias> noticias = Categorias.JsonObjectsBuild(objdataarray);

        AdaptadorCategoria adaptadornoticias = new AdaptadorCategoria(this, noticias);
        ListView lstOpciones = (ListView)findViewById(R.id.lstOpcionesA);
        lstOpciones.setAdapter(adaptadornoticias);
    }
}
