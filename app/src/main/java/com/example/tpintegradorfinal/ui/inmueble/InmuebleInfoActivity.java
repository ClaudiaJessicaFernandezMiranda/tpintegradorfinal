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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Inmueble;

public class InmuebleInfoActivity extends AppCompatActivity {

    private InmuebleViewModel InmuebleVM;
    private EditText iifEtDireccion, iifEtAmbientes, iifEtPrecio;
    private Spinner iifSTipo, iifSUso;
    private Button iifBtnModificar, iifBtnEditar;
    private RadioButton iifRbDisponible, iifRbNoDisponible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inmueble_info_agregar_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargarVistas();

        iifBtnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InmuebleVM.habilitarFormularioModificar(iifEtDireccion, iifEtAmbientes, iifSTipo, iifSUso, iifEtPrecio, iifRbDisponible, iifRbNoDisponible, iifBtnModificar, iifBtnEditar);
            }
        });

        InmuebleVM.getInmuebleSeleccionadoMutableLiveData().observe(this, new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                InmuebleVM.llenarFormularioInmueble(iifEtDireccion, iifEtAmbientes, iifSTipo, iifSUso, iifEtPrecio, iifRbDisponible, iifRbNoDisponible, inmueble);
            }
        });

        iifBtnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InmuebleVM.modificar(iifEtDireccion, iifEtAmbientes, iifSTipo, iifSUso, iifEtPrecio, iifRbDisponible, iifRbNoDisponible);
            }
        });

    }

    public void cargarVistas(){
        //LLamado Al RegistrarViewModel

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
        iifBtnModificar = findViewById(R.id.btnInmuebleAgregarModificar);
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