package com.example.andres_dell.uteqdemo.Archivos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ANDRES-DELL on 12/06/2017.
 */
public class Categorias {

    private String tituloArchivo;
    private String fechaArchivo;
    private String URLArchivo;

    public String getFechaArchivo() {
        return fechaArchivo;
    }

    public String getTituloArchivo() {
        return tituloArchivo;
    }

    public String getURLArchivo() {
        return URLArchivo;
    }

    public Categorias(JSONObject a) throws JSONException{
        tituloArchivo=a.getString("descripcion");
        fechaArchivo=a.getString("fechapub");
        URLArchivo=a.getString("URL");
    }

    public static ArrayList<Categorias> JsonObjectsBuild(JSONArray datos) throws JSONException {

        ArrayList<Categorias> noticias = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            noticias.add(new Categorias(datos.getJSONObject(i)));
        }

        return noticias;

    }
}
