package com.example.tpintegradorfinal.ui.login;

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
import com.example.tpintegradorfinal.model.Propietario;

import java.util.List;

public class PropietarioAdapter extends ArrayAdapter<Propietario> {

    private Context context;
    private List<Propietario> lista;
    private LayoutInflater li;
    public PropietarioAdapter(@NonNull Context context, int resource, @NonNull List<Propietario> objects, LayoutInflater li) {
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
            itemView = li.inflate(R.layout.item_propietario,parent,false);
        }
        Propietario propietario = lista.get(position);
        ImageView foto = itemView.findViewById(R.id.ivPropietario);
        TextView apellido = itemView.findViewById(R.id.tvApellido);
        TextView nombre = itemView.findViewById(R.id.tvNombre);

        apellido.setText(propietario.getApellido());
        nombre.setText(propietario.getNombre());
        return itemView;

    }
}
