package com.example.andres_dell.uteqdemo.Noticias;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.R;

import java.util.ArrayList;

/**
 * Created by Cristian on 2017-06-07.
 */
 public class AdaptadorNoticias extends ArrayAdapter<Noticias> {

        public AdaptadorNoticias(Context context, ArrayList<Noticias> datos) {
            super(context, R.layout.ly_listanoticias, datos);
        }





        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.ly_listanoticias, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(Html.fromHtml(getItem(position).getTitulo()));

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(Html.fromHtml(getItem(position).getSubtitulo().concat("...(Seguir leyendo)")));

            TextView lblFecha = (TextView)item.findViewById(R.id.LblFecha);
            lblFecha.setText(getItem(position).getFecha().concat(" | ").concat(getItem(position).getCategoria()));


            //cargar la imagen para cada noticia
            String URLImg="http://www.uteq.edu.ec/images/noticias/";
            String idCat=getItem(position).getIdCategoria().concat("/");
            String idNo=getItem(position).getIdNoticia().concat(".jpg");
            ImageView imgNoticia=(ImageView)item.findViewById(R.id.imgNoti);
            Glide.with(this.getContext())
                    .load(URLImg.concat(idCat).concat(idNo))
                    .crossFade()
                    .error(R.drawable.logouteqminres)
                    .into(imgNoticia);

            /*TextView lblRuta = (TextView)item.findViewById(R.id.LblRuta);
            lblRuta.setText(URLImg.concat(idCat).concat(idNo));
            TextView lblCodigo = (TextView)item.findViewById(R.id.LblCodigoN);
            lblCodigo.setText(getItem(position).getIdNoticia());*/
            return(item);
        }
 }

