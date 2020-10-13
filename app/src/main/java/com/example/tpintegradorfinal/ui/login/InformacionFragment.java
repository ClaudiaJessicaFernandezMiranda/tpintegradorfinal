package com.example.tpintegradorfinal.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;

public class InformacionFragment extends Fragment {

    private TextView ifTvInfo;
    private ImageView ifIvCasa;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_informacion, container, false);
        //Cargando Vistas
        cargarVistas(root);

        return root;
    }

    public void cargarVistas(View root){
        ifIvCasa = root.findViewById(R.id.ivImg);
        ifTvInfo = root.findViewById(R.id.tvInfomacion);
    }
}
