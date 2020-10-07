package com.example.tpintegradorfinal.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;

public class RegistrarFragment extends Fragment {

    private EditText rfEtDni, rfEtApellido, rfEtNombre, rfEtTelefono, rfEtEmail, rfEtClave;
    private Button rfBtnRegistrar;
    private RegistrarViewModel RegistrarVM;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.registrar_fragment, container, false);
        //Cargando Vistas
        cargarVistas(root);

        rfBtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarVM.registrar(rfEtDni, rfEtApellido, rfEtNombre, rfEtTelefono, rfEtEmail, rfEtClave);
            }
        });

        return root;
    }

    public void cargarVistas(View root){
        //LLamado Al RegistrarViewModel
        RegistrarVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(RegistrarViewModel.class);
        rfEtDni = root.findViewById(R.id.etDni);
        rfEtApellido = root.findViewById(R.id.etApellido);
        rfEtNombre = root.findViewById(R.id.etNombre);
        rfEtTelefono = root.findViewById(R.id.etTelefono);
        rfEtEmail = root.findViewById(R.id.etEmail);
        rfEtClave = root.findViewById(R.id.etClave);
        rfBtnRegistrar = root.findViewById(R.id.btnRegistrar);
    }
}
