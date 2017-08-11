package com.example.andres_dell.uteqdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragmento_Investigacion extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view=inflater.inflate(R.layout.fragment_fragmento__investigacion,container,false);
        return view;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_fragmento__investigacion, container, false);
    }
}
