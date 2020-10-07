package com.example.tpintegradorfinal.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel PerfilVM;
    private EditText pfEtDni, pfEtApellido, pfEtNombre, pfEtTelefono, pfEtEmail, pfEtClave;
    private Button pfBtnModificar, pfBtnEditar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.perfil_fragment, container, false);
        cargarVistas(root);

        PerfilVM.getlogeado().observe(getActivity(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                PerfilVM.llenarFormulario(pfEtDni, pfEtApellido, pfEtNombre, pfEtTelefono, pfEtEmail, pfEtClave, propietario);
            }
        });

        pfBtnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfilVM.modificar(pfEtDni, pfEtApellido, pfEtNombre, pfEtTelefono, pfEtEmail, pfEtClave);
            }
        });

        pfBtnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfilVM.editar(pfEtDni, pfEtApellido, pfEtNombre, pfEtTelefono, pfEtEmail, pfEtClave, pfBtnModificar, pfBtnEditar);
            }
        });

        PerfilVM.cargarLogeado();
        return root;
    }

    public void cargarVistas(View root){
        //LLamado Al RegistrarViewModel
        PerfilVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        pfEtDni = root.findViewById(R.id.etPerfilDni);
        pfEtApellido = root.findViewById(R.id.etPerfilApellido);
        pfEtNombre = root.findViewById(R.id.etPerfilNombre);
        pfEtTelefono = root.findViewById(R.id.etPerfilTelefono);
        pfEtEmail = root.findViewById(R.id.etPerfilEmail);
        pfEtClave = root.findViewById(R.id.etPerfilClave);
        pfBtnModificar = root.findViewById(R.id.btnPerfilModificar);
        pfBtnEditar = root.findViewById(R.id.btnPerfilHabilitarFormulario);
    }

}