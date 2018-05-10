package com.example.bolaospc.hoteles;




import com.example.bolaospc.hoteles.modulos.DatosHoteles;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.ArrayList;

/**
 * Created by BolañosPc on 09/05/2018.
 */

public interface InterfaceHoteles
{
    @GET("6uk7-fep3.json")
    Call<ArrayList<DatosHoteles>> obtenerDatos();
}
