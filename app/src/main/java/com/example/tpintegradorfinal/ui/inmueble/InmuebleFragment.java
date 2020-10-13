package com.example.tpintegradorfinal.ui.inmueble;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.util.List;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel InmuebleVM;
    private ListView ifLvInmuebles;
    private InmuebleAdapter inmuebleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inmueble_fragment, container, false);
        //Cargando Menu
        cargarVistas(root);
        //Cargando Vistas
        setHasOptionsMenu(true);

        InmuebleVM.getListaInmueblesMutableLiveData().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inmuebleAdapter = new InmuebleAdapter(getContext(), R.layout.item_inmueble, inmuebles, getLayoutInflater());
                ifLvInmuebles.setAdapter(inmuebleAdapter);
            }
        });

        ifLvInmuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(InmuebleViewModel.iniciarActividad(getActivity(), position, inmuebleAdapter));
            }
        });

        return root;
    }

    public void cargarVistas(View root){
        //LLamado Al InmuebleViewModel
        InmuebleVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        ifLvInmuebles = root.findViewById(R.id.lvInmuebles);
    }

}
