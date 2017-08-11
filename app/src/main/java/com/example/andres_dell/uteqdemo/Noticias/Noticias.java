package com.example.andres_dell.uteqdemo.Noticias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Cristian on 2017-06-07.
 */
public class Noticias {

    private String idNoticia;
    private String titulo;
    private String subtitulo;
    private String fecha;
    private String categoria;
    private String path;
    private String idCategoria;
    private String unidad;

    //private String URL;


//    public Noticias(String tit, String sub){
//        titulo = tit;
//        subtitulo = sub;
//    }

    public Noticias(JSONObject a) throws JSONException {
        idNoticia=a.getString("id");
        titulo =  a.getString("titulo");
        subtitulo=a.getString("intro").substring(0,120);
        fecha=a.get("publicacion").toString().substring(0,10);
       // categoria=a.getString("categoria");
        path=a.getString("url");
        idCategoria=a.getString("categoria_id");
        //unidad=a.getString("unidad");

    }

    public String getIdNoticia() {return idNoticia;}

    public String getTitulo(){return titulo;}

    public String getSubtitulo(){return subtitulo;}

    public String getFecha() {return fecha;}

    public String getCategoria() {return categoria;}

    public String getPath() {return path;}

    public String getIdCategoria() {return idCategoria;}

    public String getUnidad() {return unidad;}

    public static  ArrayList<Noticias> JsonObjectsBuild(JSONArray datos)
            throws JSONException {

        ArrayList<Noticias> noticias = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            noticias.add(new Noticias(datos.getJSONObject(i)));
        }

        return noticias;

    }



}
