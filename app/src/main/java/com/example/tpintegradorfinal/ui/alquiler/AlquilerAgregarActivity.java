package com.example.tpintegradorfinal.ui.alquiler;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Inmueble;
import com.example.tpintegradorfinal.model.Inquilino;

import java.util.List;

public class AlquilerAgregarActivity extends AppCompatActivity {

    private AlquilerViewModel AlquilerVM;
    private EditText aifEtPrecio, aifEtFechaInicio, aifEtFechaFin;
    private Spinner aifSInquilino, aifSInmueble;
    private Button aifBtnAgregar;
    private int idInquilino, idInmueble;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alquiler_agregar_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargarVistas();

        //Cargando SPINNER Inquilinos, Se puede hacer un adaptador personalizado yo uso el ArrayAdapter
        AlquilerVM.getListaInquilinosMutableLiveData().observe(this, new Observer<List<Inquilino>>() {
            @Override
            public void onChanged(List<Inquilino> inquilinos) {
                ArrayAdapter<Inquilino> inquilinoArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, inquilinos);
                aifSInquilino.setAdapter(inquilinoArrayAdapter);
            }
        });

        //Cargando SPINNER Inmuebles
        AlquilerVM.getListaInmueblesMutableLiveData().observe(this, new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                ArrayAdapter<Inmueble> inmuebleArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, inmuebles);
                aifSInmueble.setAdapter(inmuebleArrayAdapter);
            }
        });

        //Para Setear el ID_CORRECTAMENTE AGREGO setOnItemSelectedListener, Selecciono al Inquilino y este me devuelve su ID, el cual lo asigno a idInquilino
        aifSInquilino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inquilino inquilino = (Inquilino) parent.getItemAtPosition(position);
                idInquilino = inquilino.getIdInquilino();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Para Setear el ID_CORRECTAMENTE AGREGO setOnItemSelectedListener, Selecciono el Inmueble y este me devuelve su ID, el cual lo asigno a idInmueble
        aifSInmueble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inmueble inmueble = (Inmueble) parent.getItemAtPosition(position);
                idInmueble = inmueble.getIdInmueble();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        aifBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlquilerVM.alta(aifEtPrecio, aifEtFechaInicio, aifEtFechaFin, idInquilino, idInmueble);
            }
        });

    }

    public void cargarVistas(){
        //LLamado Al AlquilerViewModel
        AlquilerVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AlquilerViewModel.class);
        aifEtPrecio = findViewById(R.id.etAlquilerPrecio);
        aifEtFechaInicio = findViewById(R.id.etAlquilerFechaInicio);
        aifEtFechaFin = findViewById(R.id.etAlquilerFechaFin);
        aifSInquilino = findViewById(R.id.sInquilino);
        aifSInmueble = findViewById(R.id.sInmueble);
        aifBtnAgregar = findViewById(R.id.btnAlquilerAgregarModificar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    
}