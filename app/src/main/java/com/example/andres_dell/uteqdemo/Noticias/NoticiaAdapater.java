package com.example.andres_dell.uteqdemo.Noticias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.MainActivity;
import com.example.andres_dell.uteqdemo.R;

import java.util.List;

/**
 * Created by ANDRES-DELL on 01/08/2017.
 */
public class NoticiaAdapater extends RecyclerView.Adapter<NoticiaAdapater.ViewHolder> {

    Context context;
    List<Noticias> noticiasList;

    public NoticiaAdapater(Context context, List<Noticias> noticiasList){
        this.context=context;
        this.noticiasList=noticiasList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_listanoticias,parent,false);
        ViewHolder viewHolder= new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titulo.setText(Html.fromHtml(noticiasList.get(position).getTitulo()));
        holder.subtitulo.setText(Html.fromHtml(noticiasList.get(position).getSubtitulo()));
        /*holder.fechaCategoria.setText(noticiasList.get(position).getFecha().concat(" | ").
                concat(noticiasList.get(position).getCategoria()));*/
        holder.fechaCategoria.setText(noticiasList.get(position).getFecha().concat(" | "));
        //cargar la imagen para cada noticia
        String URLImg="http://www.uteq.edu.ec/";
        String url=noticiasList.get(position).getPath();
        //String idCat=noticiasList.get(position).getIdCategoria().concat("/");
        //String idNo=noticiasList.get(position).getIdNoticia().concat(".jpg");

        Glide.with(this.context)
                .load(URLImg.concat(url))
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(holder.imgNoticia);
    }

    @Override
    public int getItemCount() {
        return noticiasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo, subtitulo, fechaCategoria;
        ImageView imgNoticia;

        public ViewHolder(View item){
            super(item);

            cardView=(CardView) item.findViewById(R.id.cvListNoticias);
            titulo=(TextView) item.findViewById(R.id.LblTitulo);
            subtitulo=(TextView) item.findViewById(R.id.LblSubTitulo);
            fechaCategoria=(TextView) item.findViewById(R.id.LblFecha);
            imgNoticia=(ImageView)item.findViewById(R.id.imgNoti);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //Log.d("LogNoticiaAdapter","Noticia seleccionada"+getAdapterPosition());
                    Intent intent = new Intent(context, verNoticia.class);

                    Bundle b = new Bundle();
                    b.putString("idNoticia",noticiasList.get(getAdapterPosition()).getIdNoticia());
                    //b.putString("SubTitulo",((Noticias)a.getItemAtPosition(position)).getSubtitulo());

                    intent.putExtras(b);

                    context.startActivity(intent);
                }
            });
        }
    }
}
