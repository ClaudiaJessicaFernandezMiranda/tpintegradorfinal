package com.example.tpintegradorfinal.ui.alquiler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Inmueble;
import com.example.tpintegradorfinal.ui.inmueble.InmuebleAdapter;
import com.example.tpintegradorfinal.ui.inmueble.InmuebleViewModel;

import java.util.List;

public class AlquilerInmueblesFragment extends Fragment {

    private InmuebleViewModel InmuebleVM;
    private ListView aifLvAlquilerInmuebles;
    private InmuebleAdapter inmuebleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alquiler_inmueble_fragment, container, false);
        //Cargando Vistas
        cargarVistas(root);

        InmuebleVM.getListaInmueblesMutableLiveData().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inmuebleAdapter = new InmuebleAdapter(getContext(), R.layout.item_inmueble, inmuebles, getLayoutInflater());
                aifLvAlquilerInmuebles.setAdapter(inmuebleAdapter);
            }
        });

        aifLvAlquilerInmuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(AlquilerViewModel.iniciarActividadInmueble(getActivity(), position, inmuebleAdapter));
            }
        });


        return root;
    }

    public void cargarVistas(View root){
        //LLamado Al InmuebleViewModel
        InmuebleVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        aifLvAlquilerInmuebles = root.findViewById(R.id.lvAlquilerInmueble);
    }

}