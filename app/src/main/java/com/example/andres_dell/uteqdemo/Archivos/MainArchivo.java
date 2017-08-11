package com.example.andres_dell.uteqdemo.Archivos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andres_dell.uteqdemo.R;

public class MainArchivo extends AppCompatActivity {

    private ListView lstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_archivo);

        final String[] datos = new String[]{"Estructura Orgánico Funcional",
                "Base Legal","Reglamentos e Instructivos","Metas y Objetivos",
                "Directorio y Distributivo del Personal", "Sueldos y Beneficios",
                "Servicios y Horarios de Atención","Contratos Colectivos",
                "Guía de Trámites","Presupuesto","Auditorías","Planes y Programas Institucionales",
                "Informes de Rendición de Cuentas","Viáticos e Informes de Comisión",
                "Convenios Institucionales","Membresías Institucionales",
                "Responsable de la Información","Procesos Contractuales"
        };


        ArrayAdapter<String> adaptador2 =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);

        lstOpciones = (ListView) findViewById(R.id.lstCategoriasArchivos);

        lstOpciones.setAdapter(adaptador2);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent = new Intent(MainArchivo.this, Archivos.class);

                Bundle b = new Bundle();
                b.putInt("idCategoria",(position+1));
                b.putString("TituloCategoria",(a.getItemAtPosition(position)).toString());

                intent.putExtras(b);

                startActivity(intent);

            }
        });
    }
}
