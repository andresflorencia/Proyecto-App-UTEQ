package com.example.andres_dell.uteqdemo;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Fragmento con un diálogo personalizado
 */
public class Fragmento_Dialogo extends DialogFragment {
    public static final String TAG = "undialogo";

    String url="";
    String titulo="";

    ImageView imgNoticia;
    TextView txt;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        url=args.getString("url");
        titulo=args.getString("titulo");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View item = inflater.inflate(R.layout.imagen_dialog, null);
        builder.setView(item);

        txt=(TextView)item.findViewById(R.id.txtTituloNoticiaD);
        txt.setText(Html.fromHtml(titulo));
        imgNoticia=(ImageView)item.findViewById(R.id.imgNoticiaDial);

        Glide.with(item.getContext())
                .load(url)
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(imgNoticia);

        PhotoViewAttacher photoView=new PhotoViewAttacher(imgNoticia);
        photoView.update();

        imgNoticia.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        return builder.create();
    }




}

