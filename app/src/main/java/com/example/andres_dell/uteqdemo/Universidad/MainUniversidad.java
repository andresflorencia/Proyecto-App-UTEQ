package com.example.andres_dell.uteqdemo.Universidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andres_dell.uteqdemo.Archivos.Archivos;
import com.example.andres_dell.uteqdemo.R;

public class MainUniversidad extends AppCompatActivity {

    private ListView lstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_universidad);

        final String[] datos = new String[]{"Historia",
                "Visión - Misión","Autoridades",
                "Consejo Universitario", "Identidad Corporativa",
                "Lista de contactos"
        };

        ArrayAdapter<String> adaptador2 =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);

        lstOpciones = (ListView) findViewById(R.id.lstOpcionesUniversidad);

        lstOpciones.setAdapter(adaptador2);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent = new Intent(MainUniversidad.this, Universidad.class);
                Bundle b = new Bundle();
                b.putInt("idCategoria", position);
                b.putString("TituloCategoria", (a.getItemAtPosition(position)).toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
