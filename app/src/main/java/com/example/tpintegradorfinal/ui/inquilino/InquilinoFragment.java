package com.example.tpintegradorfinal.ui.inquilino;

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
import com.example.tpintegradorfinal.model.Inquilino;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel InquilinoVM;
    private ListView ifLvInquilino;
    private InquilinoAdapter inquilinoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        //Cargando Menu
        setHasOptionsMenu(true);
        //Cargando Vistas
        cargarVistas(root);

        InquilinoVM.getListaInquilinosMutableLiveData().observe(getActivity(), new Observer<List<Inquilino>>() {
            @Override
            public void onChanged(List<Inquilino> inquilinos) {
                inquilinoAdapter = new InquilinoAdapter(getContext(), R.layout.item_inquilino, inquilinos, getLayoutInflater());
                ifLvInquilino.setAdapter(inquilinoAdapter);
            }
        });

        ifLvInquilino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(InquilinoViewModel.iniciarActividad(getActivity(), position, inquilinoAdapter));
            }
        });


        return root;
    }

    public void cargarVistas(View root){
        //LLamado Al InquilinoViewModel
        InquilinoVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        ifLvInquilino = root.findViewById(R.id.lvInquilinos);
    }

}