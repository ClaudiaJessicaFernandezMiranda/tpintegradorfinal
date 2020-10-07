package com.example.tpintegradorfinal.ui.inmueble;

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
import com.example.tpintegradorfinal.model.Inmueble;

import java.util.List;

public class InmuebleAdapter extends ArrayAdapter<Inmueble> {

    private Context context;
    private List<Inmueble> lista;
    private LayoutInflater li;
    public InmuebleAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> objects, LayoutInflater li) {
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
            itemView = li.inflate(R.layout.item_inmueble,parent,false);
        }

        Inmueble inmueble = lista.get(position);
        ImageView foto = itemView.findViewById(R.id.ivInmueble);
        TextView direccion = itemView.findViewById(R.id.tvDireccionInmueble);
        TextView ambientes = itemView.findViewById(R.id.tvAmbientesInmueble);
        TextView precio = itemView.findViewById(R.id.tvPrecioInmueble);

        foto.setImageResource(R.drawable.inicionormal);
        direccion.setText("Dirección: "+inmueble.getDireccion());
        ambientes.setText("Ambientes: "+inmueble.getAmbientes()+"");
        precio.setText("Precio: "+inmueble.getPrecio()+"");

        return itemView;

    }
}
