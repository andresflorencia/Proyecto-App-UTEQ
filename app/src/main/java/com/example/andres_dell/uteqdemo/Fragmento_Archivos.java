package com.example.andres_dell.uteqdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.Archivos.Archivos;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Constante;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Validaciones;


public class Fragmento_Archivos extends Fragment {
    Validaciones objValidaciones=new Validaciones();
    Constante objConstante=new Constante();
    private ListView lstOpciones;
    ArrayAdapter<String> adp2;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_fragmento__archivos, container,false);

        final String[] datos = new String[]{"Estructura Orgánico Funcional",
                "Base Legal","Reglamentos e Instructivos","Metas y Objetivos",
                "Directorio y Distributivo del Personal", "Sueldos y Beneficios",
                "Servicios y Horarios de Atención","Contratos Colectivos",
                "Guía de Trámites","Presupuesto","Auditorías","Planes y Programas Institucionales",
                "Informes de Rendición de Cuentas","Viáticos e Informes de Comisión",
                "Convenios Institucionales","Membresías Institucionales",
                "Responsable de la Información","Procesos Contractuales"
        };

        adp2=new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, datos);

        try {
            //uso del metodo de verificacion de conexion a internet
            if (!objValidaciones.verificaConexion(view.getContext())) {
                Toast.makeText(view.getContext(),
                        objConstante.getMensajeSinConexion(), Toast.LENGTH_LONG)
                        .show();
                //this.finish();
                // fin de "uso del metodo de verificacion de conexion a internet"
            }
            else {

                lstOpciones = (ListView) view.findViewById(R.id.lstCategoriasA);

                lstOpciones.setAdapter(adp2);

                lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                        Intent intent = new Intent(getView().getContext(), Archivos.class);

                        Bundle b = new Bundle();
                        b.putInt("idCategoria",(position+1));
                        b.putString("TituloCategoria",(a.getItemAtPosition(position)).toString());

                        intent.putExtras(b);

                        startActivity(intent);

                    }
                });
            }
        }catch (Exception e){
            Snackbar.make(container, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }



        //"refrescar deslizando"
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorFecha, R.color.colorPrimary);//Aqui podemos cponer los colores que queremos
        //que se vayan mostrando en el progress circle
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Aqui debemos poner que es lo que se quieres hacer cuando deslizamos para refrescar
                        lstOpciones.setAdapter(adp2);
                        //fin de "Aqui debemos poner que es lo que se quieres hacer cuando deslizamos para refrescar"
                        //Finalizar el progress circle
                        Fragmento_Archivos.this.swipeRefreshLayout.setRefreshing(false);//Se debe sustituir "Archivo" por la activity donde
                        // esta el "SwipeRefreshLayout", -es el que esta aqui-
                    }
                });
        //fin de "refrescar deslizando"

        // Inflate the layout for this fragment


        return view;
}
}
