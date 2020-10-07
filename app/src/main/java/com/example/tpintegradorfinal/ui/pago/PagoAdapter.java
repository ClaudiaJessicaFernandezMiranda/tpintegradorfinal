package com.example.tpintegradorfinal.ui.pago;

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
import com.example.tpintegradorfinal.model.Pago;

import java.util.List;

public class PagoAdapter extends ArrayAdapter<Pago> {

    private Context context;
    private List<Pago> lista;
    private LayoutInflater li;
    public PagoAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects, LayoutInflater li) {
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
            itemView = li.inflate(R.layout.item_pago,parent,false);
        }

        Pago pago = lista.get(position);
        ImageView foto = itemView.findViewById(R.id.ivPago);
        TextView nroPago = itemView.findViewById(R.id.tvPagoNroPago);
        TextView fecha = itemView.findViewById(R.id.tvPagoFecha);
        TextView importe = itemView.findViewById(R.id.tvPagoImporte);


        foto.setImageResource(R.drawable.pagosnormal);
        nroPago.setText(String.format("Numero de pago: %s", pago.getNro_pago()));
        fecha.setText(String.format("Fecha: %s", pago.getFecha()));
        importe.setText(String.format("Importe: %s", pago.getImporte()));

        return itemView;

    }
}
