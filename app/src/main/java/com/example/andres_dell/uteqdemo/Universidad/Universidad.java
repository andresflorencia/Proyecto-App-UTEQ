package com.example.andres_dell.uteqdemo.Universidad;

import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.R;
import com.example.andres_dell.uteqdemo.Validaciones;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class Universidad extends AppCompatActivity{

    Validaciones objValidaciones=new Validaciones();
    WebView webview;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universidad);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = this.getIntent().getExtras();

        actionBar.setTitle(bundle.getString("TituloCategoria"));
        String urlWebService="";
        //TextView txtContenido=(TextView)findViewById(R.id.txtContenido);
        pos=bundle.getInt("idCategoria");
        if(pos==0)
        {
            urlWebService="http://186.46.90.102/webservice/universidad/historia";
        }
        else if(pos==1){
            urlWebService="http://186.46.90.102/webservice/universidad/acercade";
        }
        else if(pos==2){
            urlWebService="http://186.46.90.102/webservice/universidad/autoridades";
        }
        else if(pos==3){
            urlWebService="http://186.46.90.102/webservice/universidad/consejo";
        }
        else if(pos==4){
            urlWebService="http://186.46.90.102/webservice/universidad/identidad";
        }

        /*Map<String, String> params = new HashMap<String, String>();
        WebService ws= new WebService(urlWebService,params, Universidad.this, Universidad.this);
        ws.execute("");*/
        //uso del metodo de verificacion de conexion a internet
        if (!objValidaciones.verificaConexion(this)) {
            Toast.makeText(this,
                    "Comprueba tu conexión a Internet", Toast.LENGTH_LONG)
                    .show();
            //this.finish();
            // fin de "uso del metodo de verificacion de conexion a internet"
        }
        else {


            //Progress para los web view
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.BUTTON_POSITIVE);//Aqui podemos cambiar el estilo del mensaje de carga
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
            webview=(WebView) findViewById(R.id.wvMenuUniversidaad);
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

            webview.loadUrl(urlWebService);
            webview.setWebViewClient(new WebViewClient(){
                // android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
                //android:configChanges="keyboard|keyboardHidden|orientation|screenSize">

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    //esto elimina ProgressBar.
                    progressDialog.dismiss();
                }

            });
        }

    }
/*
    @Override
    public void processFinish(String result) throws JSONException {

        JSONArray objdataarray= new JSONArray (result);
        //ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

        JSONObject jsonObj= objdataarray.getJSONObject(0);

        //Extrayendo datos del JSON e insertando en cada campo
        TextView txtTituloOpcionU = (TextView)findViewById(R.id.txtCategUniversidad);
        txtTituloOpcionU.setText(Html.fromHtml(jsonObj.getString("titulo")));

        if (pos!=1){

        TextView txtContenidoU = (TextView)findViewById(R.id.txtContenido);
        txtContenidoU.setText(Html.fromHtml(jsonObj.getString("contenido")));}
       else{
            String urlImg="http://www.uteq.edu.ec/";
            TextView txtVisionT=(TextView) findViewById(R.id.txtVisionT); txtVisionT.setVisibility(View.VISIBLE);
            TextView txtVision=(TextView) findViewById(R.id.txtVision);
            txtVision.setVisibility(View.VISIBLE);
            txtVision.setText(Html.fromHtml(jsonObj.getString("vision")));

            TextView txtMisionT=(TextView) findViewById(R.id.txtMisionT); txtMisionT.setVisibility(View.VISIBLE);
            TextView txtMision=(TextView) findViewById(R.id.txtMision);
            txtMision.setVisibility(View.VISIBLE);
            txtMision.setText(Html.fromHtml(jsonObj.getString("mision")));

            TextView txtDireccion=(TextView) findViewById(R.id.txtDireccion);
            txtDireccion.setVisibility(View.VISIBLE);
            txtDireccion.setText(Html.fromHtml(jsonObj.getString("direccion")));

            TextView txtTelefono=(TextView) findViewById(R.id.txtTelefono);
            txtTelefono.setVisibility(View.VISIBLE);
            txtTelefono.setText(Html.fromHtml(jsonObj.getString("telefono")));

            TextView txtCorreo=(TextView) findViewById(R.id.txtCorreo);
            txtCorreo.setVisibility(View.VISIBLE);
            txtCorreo.setText(Html.fromHtml(jsonObj.getString("correo")));

            ImageView imgMenuU=(ImageView)findViewById(R.id.imgOpcionMenuU);
            String idCat=jsonObj.getString("url");
            Glide.with(this)
                    .load(urlImg.concat(idCat))
                    .crossFade()
                    .error(R.drawable.autoridad)
                    .into(imgMenuU);
       }

    }*/
}
