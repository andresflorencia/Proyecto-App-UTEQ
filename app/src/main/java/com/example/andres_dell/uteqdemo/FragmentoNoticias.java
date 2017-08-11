package com.example.andres_dell.uteqdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.Noticias.NoticiaAdapater;
import com.example.andres_dell.uteqdemo.Noticias.Noticias;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentoNoticias extends Fragment implements Asynchtask {

    //////////////////////////////////////
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    //////////////////////////////////////
    Validaciones objValidaciones=new Validaciones();
    SwipeRefreshLayout swipeRefreshLayout;
    View view;

    String idTipoNoticia="Noticias";
    String urlWebService="";
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        idTipoNoticia=args.getString("idTipoNoticia");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view=inflater.inflate(R.layout.fragment_fragmento_noticias, container,false);
        ////////////////////////////////////
        recyclerView=(RecyclerView)view.findViewById(R.id.rvListNoticiasN);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ///////////////////////////////////

        //uso del metodo de verificacion de conexion a internet
        if (!objValidaciones.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(),
                    "Comprueba tu conexión a Internet", Toast.LENGTH_LONG)
                    .show();
            //this.finish();
            // fin de "uso del metodo de verificacion de conexion a internet"
        }
        else{
            if(idTipoNoticia.equals("Noticias")){
                urlWebService="http://186.46.90.102/webservice/noticia";
            }else if(idTipoNoticia.equals("Investigacion")){
                urlWebService="http://186.46.90.102/webservice/noticia/investigacion";
            }else if(idTipoNoticia.equals("Vinculacion")){
                urlWebService="http://186.46.90.102/webservice/noticia/vinculacion";
            }
            Map<String, String> params = new HashMap<String,String>();
            WebService ws= new WebService(urlWebService,
                    params, view.getContext(),
                     FragmentoNoticias.this);
            ws.execute("");
        }




        //"refrescar deslizando"
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeLayoutN);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorFecha, R.color.colorAccent);//Aqui podemos cponer los colores que queremos
        //que se vayan mostrando en el progress circle
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Aqui debemos poner que es lo que se quieres hacer cuando deslizamos para refrescar
                        Map<String, String> params = new HashMap<String,String>();

                        WebService ws= new WebService(urlWebService,
                                params, view.getContext(),
                                FragmentoNoticias.this);
                        ws.execute("");
                        //fin de "Aqui debemos poner que es lo que se quieres hacer cuando deslizamos para refrescar"
                        //Finalizar el progress circle
                        FragmentoNoticias.this.swipeRefreshLayout.setRefreshing(false);//Se debe sustituir "Archivo" por la activity donde
                        // esta el "SwipeRefreshLayout", -es el que esta aqui-
                    }
                });
        //fin de "refrescar deslizando"
        return view;
        //return inflater.inflate(R.layout.fragment_fragmento_noticias, container, false);
    }


    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray= new JSONArray (result);
        ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

        //AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);

        NoticiaAdapater noticiaAdapater=new NoticiaAdapater(view.getContext(), noticias);
        recyclerView.setAdapter(noticiaAdapater);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
