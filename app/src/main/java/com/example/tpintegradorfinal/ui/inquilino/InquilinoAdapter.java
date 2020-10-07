package com.example.tpintegradorfinal.ui.inquilino;

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
import com.example.tpintegradorfinal.model.Inquilino;

import java.util.List;

public class InquilinoAdapter extends ArrayAdapter<Inquilino> {

    private Context context;
    private List<Inquilino> lista;
    private LayoutInflater li;
    public InquilinoAdapter(@NonNull Context context, int resource, @NonNull List<Inquilino> objects, LayoutInflater li) {
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
            itemView = li.inflate(R.layout.item_inquilino,parent,false);
        }

        Inquilino inquilino = lista.get(position);
        ImageView foto = itemView.findViewById(R.id.ivInquilino);
        TextView nobreApe = itemView.findViewById(R.id.tvNombreApeInquilino);
        TextView telefono = itemView.findViewById(R.id.tvTelefonoInquilino);
        TextView direccion = itemView.findViewById(R.id.tvDireccionInquilino);

        foto.setImageResource(R.drawable.inquilinosnormal);

        nobreApe.setText(String.format("Nombre: %s %s", inquilino.getNombre(), inquilino.getApellido()));
        telefono.setText(String.format("Telefono: %s", inquilino.getTelefono()));
        direccion.setText(String.format("Dirección: %s", inquilino.getDireccion()));

        return itemView;

    }
}
