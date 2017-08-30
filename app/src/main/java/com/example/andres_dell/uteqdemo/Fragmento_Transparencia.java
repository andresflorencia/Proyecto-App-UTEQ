package com.example.andres_dell.uteqdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.Transparencia.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Fragmento_Transparencia extends Fragment {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private ArrayList<String> lsAnios=new ArrayList<>();
    private ArrayList<String> lsMeses=new ArrayList<>();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    int idGrupo;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragmento__transparencia, container, false);



        listView = (ExpandableListView)view.findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(view.getContext(),listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        return view;
    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        lsAnios.add("2016");
        lsAnios.add("2015");
        lsAnios.add("2014");
        listDataHeader.addAll(lsAnios);

        int nDeAnios=3;

        final List<String> fase = new ArrayList<>();
        fase.add("Enero");
        fase.add("Febrero");
        fase.add("Marzo");
        fase.add("Abril");
        fase.add("Mayo");
        fase.add("Junio");

        for(int i =0;i<nDeAnios;i++){
            listHash.put(listDataHeader.get(i),fase);
        }

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(!parent.isGroupExpanded(groupPosition))
                    parent.expandGroup(groupPosition);
                else
                    parent.collapseGroup(groupPosition);
                idGrupo=groupPosition;
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(view.getContext(), "Grupo: "+lsAnios.get(groupPosition) +" - Item: "+ fase.get(childPosition)
                        , Toast.LENGTH_SHORT).show();

                Bundle b = new Bundle();
                b.putString("idTipoNoticia","Vinculacion");
                b.putString("bundleSlider","");
                Fragmento_ArchivosTransparencia fragmentoArchTr=new Fragmento_ArchivosTransparencia();
                fragmentoArchTr.setArguments(b);

                fragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, fragmentoArchTr).commit();
                return true;
            }
        });
    }
}
