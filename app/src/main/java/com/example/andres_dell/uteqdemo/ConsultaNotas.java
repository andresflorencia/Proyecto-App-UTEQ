package com.example.andres_dell.uteqdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ConsultaNotas extends AppCompatActivity {

    WebView webview;

    @Override
    public void onBackPressed(){
        if (webview.canGoBack())
            webview.goBack();
        else
            super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_notas);

        webview=(WebView) findViewById(R.id.wvConsultaNotasSicau);

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

        webview.loadUrl("http://sicau.uteq.edu.ec/portal/estudiantes/notas/home.faces");
        webview.setWebViewClient(new WebViewClient());
    }
}
