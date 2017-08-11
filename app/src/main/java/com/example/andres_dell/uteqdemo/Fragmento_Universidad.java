package com.example.andres_dell.uteqdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.ClasesComplementarias.Constante;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Validaciones;
import com.example.andres_dell.uteqdemo.Universidad.Universidad;


public class Fragmento_Universidad extends Fragment{
    Validaciones objValidaciones=new Validaciones();
    Constante objConstante=new Constante();
    private ListView lstOpciones;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_fragmento__universidad, container,false);

        final String[] datos = new String[]{"Historia",
                "Visión - Misión","Autoridades",
                "Consejo Universitario", "Identidad Corporativa",
                "Lista de contactos"
        };

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
                ArrayAdapter<String> adp2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, datos);
                lstOpciones = (ListView) view.findViewById(R.id.lstCategoriasU);

                lstOpciones.setAdapter(adp2);

                lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                        Intent intent = new Intent(getView().getContext(), Universidad.class);
                        Bundle b = new Bundle();
                        b.putInt("idCategoria", position);
                        b.putString("TituloCategoria", (a.getItemAtPosition(position)).toString());
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
            }
        }catch (Exception e){
            Snackbar.make(container, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }





        // Inflate the layout for this fragment


        return view;
        //return inflater.inflate(R.layout.fragment_fragmento__universidad, container, false);
    }
}
