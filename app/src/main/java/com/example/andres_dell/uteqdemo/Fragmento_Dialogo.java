package com.example.andres_dell.uteqdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Fragmento con un diálogo personalizado
 */
public class Fragmento_Dialogo extends DialogFragment {
    private static final String TAG = Fragmento_Dialogo.class.getSimpleName();

    String url="";
    String titulo="";
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

        TextView txt=(TextView)item.findViewById(R.id.txtTituloNoticiaD);
        txt.setText(Html.fromHtml(titulo));
        ImageView imgNoticia=(ImageView)item.findViewById(R.id.imgNoticiaDial);

        Glide.with(item.getContext())
                .load(url)
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(imgNoticia);


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

