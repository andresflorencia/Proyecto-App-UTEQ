package com.example.andres_dell.uteqdemo.ClasesComplementarias;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ANDRES-DELL on 06/08/2017.
 */
public class Validaciones implements Asynchtask {


    private HashMap<String,String> datos=new HashMap<String, String>();

    public HashMap<String, String> getDatos() {return datos;}

    public void setDatos(HashMap<String, String> datos) {this.datos = datos;}

    // public static WebView webview;
    //metodo verficar conexion a internet
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }
    //fin de "metodo verficar conexion a internet"



    //Método temporal para la validación de la red a la que está conectado el usuario
    public static String ssid(Context ctx){
        try {
            WifiManager wifiMgr = (WifiManager) ctx.getSystemService(ctx.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            return ssid;
        }
        catch (Exception ex){
            return "Error: "+ ex;
        }
    }
    public static String ipAConetarse(Context ctx){
        Constante constante=new Constante();
        String ssid=ssid(ctx);
        String ipRetorno=constante.getIpPublica();
        if (ssid!=null && ssid != ""&&(ssid=="Comedor Universitario"||ssid=="lidertics1"||ssid=="Wifi_UTEQ"||ssid=="Docentes"))
            ipRetorno=constante.getIpLocal();
        return ipRetorno;
    }


    public void EjecutarWebService(String url, View view){


        Map<String, String> paramsS = new HashMap<String,String>();
        WebService wsS= new WebService(url,
                paramsS, view.getContext(),
                (Asynchtask) view.getContext());
        wsS.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONArray objdataarray= new JSONArray (result);

        for (int i = 0; i < objdataarray.length(); i++) {
            this.datos.put(objdataarray.getJSONObject(i).getString("titulo"),objdataarray.getJSONObject(i).getString("img"));
        }

    }
}
