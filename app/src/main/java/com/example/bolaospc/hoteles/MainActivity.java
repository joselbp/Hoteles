package com.example.bolaospc.hoteles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bolaospc.hoteles.Adapter.AdapterHoteles;
import com.example.bolaospc.hoteles.modulos.DatosHoteles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private AdapterHoteles rvAdapter;
    private boolean aptoParaCargar;
    final String TAG = "HOTELES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        retrofit = new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/").addConverterFactory(GsonConverterFactory.create()).build();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        rvAdapter = new AdapterHoteles(this);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy >0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(aptoParaCargar) {
                        if ((visibleItemCount +pastVisibleItems ) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final");
                            aptoParaCargar = false;

                            procesarDatos();
                        }
                    }
                }
            }
        });


        aptoParaCargar = true;
        procesarDatos();
    }

    private void procesarDatos() {


        InterfaceHoteles service = retrofit.create(InterfaceHoteles.class);

        Call<ArrayList<DatosHoteles>> respuestaApi = service.obtenerDatos();

        respuestaApi.enqueue(new Callback<ArrayList<DatosHoteles>>() {
            @Override
            public void onResponse(Call<ArrayList<DatosHoteles>> call, Response<ArrayList<DatosHoteles>> response)
            {
                if (response.isSuccessful())
                {
                    aptoParaCargar = true;
                    ArrayList<DatosHoteles>  empresas = response.body();


                    rvAdapter.adicionarDato(empresas);



                }
            }

            @Override
            public void onFailure(Call<ArrayList<DatosHoteles>> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, t.getMessage());
            }


        });

    }





}

