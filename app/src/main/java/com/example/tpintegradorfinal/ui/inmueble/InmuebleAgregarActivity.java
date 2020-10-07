package com.example.tpintegradorfinal.ui.inmueble;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;

public class InmuebleAgregarActivity extends AppCompatActivity {

    private InmuebleViewModel InmuebleVM;
    private EditText iifEtDireccion, iifEtAmbientes, iifEtPrecio;
    private Spinner iifSTipo, iifSUso;
    private Button iifBtnAgregar, iifBtnEditar;
    private RadioButton iifRbDisponible, iifRbNoDisponible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inmueble_info_agregar_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargarVistas();

        iifBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InmuebleVM.alta(iifEtDireccion, iifEtAmbientes, iifSTipo, iifSUso, iifEtPrecio, iifRbDisponible, iifRbNoDisponible);
            }
        });

        InmuebleVM.habilitarFormulacioAgregar(iifEtDireccion, iifEtAmbientes, iifSTipo, iifSUso, iifEtPrecio, iifRbDisponible, iifRbNoDisponible, iifBtnAgregar, iifBtnEditar);
    }

    public void cargarVistas(){
        //LLamado Al InmuebleViewModel
        InmuebleVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(InmuebleViewModel.class);
        iifEtDireccion = findViewById(R.id.etInmuebleDireccion);
        iifEtAmbientes = findViewById(R.id.etInmuebleAmbientes);
        iifSTipo = findViewById(R.id.sTipo);
        iifSTipo.setEnabled(false);
        iifSUso = findViewById(R.id.sUso);
        iifSUso.setEnabled(false);
        iifEtPrecio = findViewById(R.id.etInmueblePrecio);
        iifRbDisponible = findViewById(R.id.rbInmuebleDisponible);
        iifRbNoDisponible = findViewById(R.id.rbInmuebleNoDisponible);
        iifBtnAgregar = findViewById(R.id.btnInmuebleAgregarModificar);
        iifBtnEditar = findViewById(R.id.btnInmueleHabilitarFormulario);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    
}