package com.example.andres_dell.uteqdemo.Archivos;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andres_dell.uteqdemo.R;

import java.util.ArrayList;

/**
 * Created by ANDRES-DELL on 12/06/2017.
 */
public class AdaptadorCategoria extends ArrayAdapter<Categorias> {

public AdaptadorCategoria(Context context, ArrayList<Categorias> datos) {
        super(context, R.layout.ly_listaarchivos, datos);
        }





public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_listaarchivos, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
        lblTitulo.setText(Html.fromHtml(getItem(position).getTituloArchivo()));

        TextView lblFecha = (TextView)item.findViewById(R.id.LblFecha);
        lblFecha.setText(getItem(position).getFechaArchivo());

        TextView lblURLImg = (TextView)item.findViewById(R.id.LblUrlImg);
        lblURLImg.setText(getItem(position).getURLArchivo());
        //lblURLImg.setSoundEffectsEnabled(true);


        /*cargar la imagen para cada noticia
        String URLImg="http://www.uteq.edu.ec/images/noticias/";
        String idCat=getItem(position).getIdCategoria().concat("/");
        String idNo=getItem(position).getIdNoticia().concat(".jpg");
        ImageView imgNoticia=(ImageView)item.findViewById(R.id.imgNoti);
        Glide.with(this.getContext())
        .load(URLImg.concat(idCat).concat(idNo))
        .into(imgNoticia);

            /*TextView lblRuta = (TextView)item.findViewById(R.id.LblRuta);
            lblRuta.setText(URLImg.concat(idCat).concat(idNo));
            TextView lblCodigo = (TextView)item.findViewById(R.id.LblCodigoN);
            lblCodigo.setText(getItem(position).getIdNoticia());*/
        return(item);
        }
}
