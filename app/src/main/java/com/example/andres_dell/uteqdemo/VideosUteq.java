package com.example.andres_dell.uteqdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VideosUteq extends AppCompatActivity {

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
        setContentView(R.layout.activity_videos_uteq);

        //Progress para los web view
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.BUTTON_POSITIVE);//Aqui podemos cambiar el estilo del mensaje de carga
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        webview=(WebView) findViewById(R.id.wvVideosUteq);

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
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //esto elimina ProgressBar.
                progressDialog.dismiss();
            }
        });
    }
}
