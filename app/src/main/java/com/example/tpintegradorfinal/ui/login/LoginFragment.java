package com.example.tpintegradorfinal.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tpintegradorfinal.MainActivity;
import com.example.tpintegradorfinal.R;

import java.io.Serializable;
import java.util.List;

public class LoginFragment extends Fragment {

    private LoginViewModel LoginVM;
    private EditText lfEtEmail, lfEtClave;
    private Button lfBtnIngresar;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        cargarVistas(root);

        lfBtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginVM.login(lfEtEmail, lfEtClave);
            }
        });

        LoginVM.getIniciarSesion().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean resultado) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

        return root;
    }

    public void cargarVistas(View root){
        LoginVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(LoginViewModel.class);
        lfEtEmail = root.findViewById(R.id.etEmail);
        lfEtClave = root.findViewById(R.id.etClave);
        lfBtnIngresar = root.findViewById(R.id.btnIngresar);
    }

}