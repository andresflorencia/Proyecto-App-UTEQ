package com.example.andres_dell.uteqdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.ClasesComplementarias.Constante;
import com.example.andres_dell.uteqdemo.ClasesComplementarias.Validaciones;
import com.example.andres_dell.uteqdemo.Noticias.NoticiaAdapater;
import com.example.andres_dell.uteqdemo.Noticias.Noticias;
import com.example.andres_dell.uteqdemo.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class FragmentoNoticias extends Fragment implements Asynchtask, BaseSliderView.OnSliderClickListener {


    SliderLayout mDemoSlider;
    HashMap<String,String> url_maps=new HashMap<>();
    MainActivity mainActivity=new MainActivity();
    //////////////////////////////////////
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    //////////////////////////////////////
    Validaciones objValidaciones=new Validaciones();
    Constante objConstante=new Constante();
    SwipeRefreshLayout swipeRefreshLayout;
    View view;

    String idTipoNoticia="Noticias";
    String urlWebService="";
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        idTipoNoticia=args.getString("idTipoNoticia");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_fragmento_noticias, container,false);



        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        ////////////////////////////////////
        recyclerView=(RecyclerView)view.findViewById(R.id.rvListNoticiasN);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ///////////////////////////////////

        //uso del metodo de verificacion de conexion a internet
        if (!objValidaciones.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(),
                    objConstante.getMensajeSinConexion(), Toast.LENGTH_LONG)
                    .show();
            //this.finish();
            // fin de "uso del metodo de verificacion de conexion a internet"
        }
        else{
            String ip=objValidaciones.ipAConetarse(view.getContext());
            if(idTipoNoticia.equals("Noticias")){
                urlWebService="http://"+ip+objConstante.getWsNoticia();
            }else if(idTipoNoticia.equals("Investigacion")){
                urlWebService="http://"+ip+objConstante.getWsNoticiaInvestigacion();
            }else if(idTipoNoticia.equals("Vinculacion")){
                urlWebService="http://"+ip+objConstante.getWsNoticiaVinculacion();
            }
            Map<String, String> params = new HashMap<String,String>();
            WebService ws= new WebService(urlWebService,
                    params, view.getContext(),
                     FragmentoNoticias.this);
            ws.execute("");

            /////////////SLIDER//////////

            /*url_maps.put("Aqui poner alguna wa", "http://www.uteq.edu.ec/images/slider/slider-01.jpg");
            url_maps.put("Aqui poner otra wa", "http://www.uteq.edu.ec/images/slider/pp_slider-01.jpg");
            url_maps.put("Aqui poner una tercer wa", "http://www.uteq.edu.ec/images/slider/correo-01.jpg");*/

            this.url_maps=mainActivity.url_maps;
            for(String name : url_maps.keySet()){
                TextSliderView textSliderView = new TextSliderView(view.getContext());
                // initialize a SliderLayout
                textSliderView
                        .description(name)
                        .image(objConstante.getUrlImgSlider()+url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);

                mDemoSlider.addSlider(textSliderView);
            }

            //Efecto de transicion: Aqui solo dejar uno de estos, el que mas guste
            //Como recomendación se podría poner configurable
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(6000);
            /////////////////FIN SLIDER//////////////////////////////


        }




        //"refrescar deslizando"
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeLayoutN);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorFecha, R.color.colorAccent);//Aqui podemos cponer los colores que queremos
        //que se vayan mostrando en el progress circle
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Aqui debemos poner que es lo que se quieres hacer cuando deslizamos para refrescar
                        Map<String, String> params = new HashMap<String,String>();

                        WebService ws= new WebService(urlWebService,
                                params, view.getContext(),
                                FragmentoNoticias.this);
                        ws.execute("");
                        //fin de "Aqui debemos poner que es lo que se quieres hacer cuando deslizamos para refrescar"
                        //Finalizar el progress circle
                        FragmentoNoticias.this.swipeRefreshLayout.setRefreshing(false);//Se debe sustituir "Archivo" por la activity donde
                        // esta el "SwipeRefreshLayout", -es el que esta aqui-
                    }
                });
        //fin de "refrescar deslizando"
        return view;
        //return inflater.inflate(R.layout.fragment_fragmento_noticias, container, false);
    }


    //////////////MÉTODOS DEL SLIDER//////////
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Aqui se puede poner lo que se quiere abrir o ejecutar al darle clic a un elemento del slider
        Toast.makeText(view.getContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    //////////FIN DE MÉTODOS DEL SLIDER////////////

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray= new JSONArray (result);
        ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

        //AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);

        NoticiaAdapater noticiaAdapater=new NoticiaAdapater(view.getContext(), noticias);
        recyclerView.setAdapter(noticiaAdapater);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
