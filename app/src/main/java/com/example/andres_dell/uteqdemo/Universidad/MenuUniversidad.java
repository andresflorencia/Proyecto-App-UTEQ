package com.example.andres_dell.uteqdemo.Universidad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ANDRES-DELL on 09/08/2017.
 */
public class MenuUniversidad {

    private String id;
    private String unidad_id;
    private String titulo;
    private String contenido;
    private String estado;

    public MenuUniversidad(JSONObject a)throws JSONException{
        id=a.getString("id");
        unidad_id=a.getString("unidad_id");
        titulo=a.getString("titulo");
        contenido=a.getString("contenido");
        estado=a.getString("estado");
    }

    public String getId() {return id;}

    public String getUnidad_id() {return unidad_id;}

    public String getTitulo() {return titulo;}

    public String getContenido() {return contenido;}

    public String getEstado() {return estado;}

    public static ArrayList<MenuUniversidad> JsonObjectsBuild(JSONArray datos)
            throws JSONException {

        ArrayList<MenuUniversidad> menuUniversidadArrayList = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            menuUniversidadArrayList.add(new MenuUniversidad(datos.getJSONObject(i)));
        }

        return menuUniversidadArrayList;

    }
}

