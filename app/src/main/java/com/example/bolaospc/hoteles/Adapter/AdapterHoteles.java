package com.example.bolaospc.hoteles.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bolaospc.hoteles.R;
import com.example.bolaospc.hoteles.modulos.DatosHoteles;

import java.util.ArrayList;

public class AdapterHoteles extends RecyclerView.Adapter<AdapterHoteles.DireccionViewHolder>{

    ArrayList<DatosHoteles> datos;

    private DatosHoteles datos1;
    private Context context;


    public AdapterHoteles(Context context)
    {

        this.context = context;
        datos = new ArrayList<>();
    }


    AdapterHoteles(ArrayList<DatosHoteles> datos)

    {
        this.datos = datos;
    }


    public class DireccionViewHolder extends RecyclerView.ViewHolder
    {

        private TextView text1;
        private TextView text2;
        private TextView text3;






        CardView cv;


        DireccionViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            text1 = (TextView) itemView.findViewById(R.id.txt1);
            text2 = (TextView) itemView.findViewById(R.id.txt2);
            text3 = (TextView) itemView.findViewById(R.id.txt3);

        }
    }



    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public DireccionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        //item
        DireccionViewHolder pvh = new DireccionViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(DireccionViewHolder direccionViewHolder, int i) {
        datos1 = datos.get(i);
     //  direccionViewHolder.direccion.setText(datos1.getDirecciNPvd());
        direccionViewHolder.text1.setText("Telefono: "+ datos1.getContactoTelefNico());
        direccionViewHolder.text2.setText("Dirección: "+datos1.getDirecciN());
        direccionViewHolder.text3.setText("Nombre: "+datos1.getNombreDelHotel());
        Glide.with(context);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void adicionarDato(ArrayList <DatosHoteles> listaDatos){
        datos.addAll(listaDatos);
        notifyDataSetChanged();
    }
}
