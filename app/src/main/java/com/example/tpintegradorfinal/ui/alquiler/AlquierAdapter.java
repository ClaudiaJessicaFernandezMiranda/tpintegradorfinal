package com.example.tpintegradorfinal.ui.alquiler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Alquiler;

import java.util.List;

public class AlquierAdapter extends ArrayAdapter<Alquiler> {

    private Context context;
    private List<Alquiler> lista;
    private LayoutInflater li;
    public AlquierAdapter(@NonNull Context context, int resource, @NonNull List<Alquiler> objects, LayoutInflater li) {
        super(context, resource, objects);
        this.context = context;
        this.lista = objects;
        this.li = li;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null){
            itemView = li.inflate(R.layout.item_alquiler,parent,false);
        }

        Alquiler alquiler = lista.get(position);
        ImageView foto = itemView.findViewById(R.id.ivAlquiler);
        TextView precio = itemView.findViewById(R.id.tvAlquilerPrecio);
        TextView fechaInicio = itemView.findViewById(R.id.tvAlquilerFechaInicio);
        TextView fechaFin = itemView.findViewById(R.id.tvAlquilerFechaFin);

        foto.setImageResource(R.drawable.contratosnormal);
        precio.setText(String.format("Precio: %s", alquiler.getPrecio()));
        fechaInicio.setText(String.format("Fecha Inicio: %s", alquiler.getFecha_inicio()));
        fechaFin.setText(String.format("Fecha Fin: %s", alquiler.getFecha_fin()));

        return itemView;

    }
}
