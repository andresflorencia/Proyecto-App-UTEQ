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

/**
 * Created by ANDRES-DELL on 06/08/2017.
 */
public class Validaciones {


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
        if (ssid!=null && ssid != ""&&(ssid=="Comedor Universitario" || ssid=="TOALA_CNT"||ssid=="lidertics1"||ssid=="Wifi_UTEQ"||ssid=="Docentes"))
            ipRetorno=constante.getIpLocal();
        return ipRetorno;
    }
}
