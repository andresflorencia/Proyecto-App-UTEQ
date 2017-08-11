package com.example.andres_dell.uteqdemo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CanalYotube extends Fragment {

    Validaciones objValidaciones=new Validaciones();
    WebView webview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_canal_yotube, container,false);



        //uso del metodo de verificacion de conexion a internet
        if (!objValidaciones.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(),
                    "Comprueba tu conexión a Internet", Toast.LENGTH_LONG)
                    .show();
            //this.finish();
            // fin de "uso del metodo de verificacion de conexion a internet"
        }
        else {


            //Progress para los web view
            final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setProgressStyle(ProgressDialog.BUTTON_POSITIVE);//Aqui podemos cambiar el estilo del mensaje de carga
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
            webview=(WebView) view.findViewById(R.id.wvVideosUteq);
            //JavaScript
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setFocusable(true);
            webview.setFocusableInTouchMode(true);

            //Set render priority to high
            webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webview.getSettings().setDomStorageEnabled(true);
            webview.getSettings().setDatabaseEnabled(true);
            webview.getSettings().setAppCacheEnabled(true);
            webview.getSettings().setBuiltInZoomControls(true);

            webview.loadUrl("https://www.youtube.com/user/UTEQCHANNEL/videos");
            webview.setWebViewClient(new WebViewClient(){
                // android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
                //android:configChanges="keyboard|keyboardHidden|orientation|screenSize">
                @Override
                public void onLoadResource(WebView webView, String url){
                    super.onLoadResource(webview,url);
                    progressDialog.setProgress(webView.getProgress());
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    //esto elimina ProgressBar.
                    progressDialog.dismiss();
                }

            });
        }
        // Inflate the layout for this fragment
        return view;
        //return inflater.inflate(R.layout.fragment_canal_yotube, container, false);
    }

}
