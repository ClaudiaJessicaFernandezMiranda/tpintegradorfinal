package com.example.tpintegradorfinal.ui.pago;

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
import com.example.tpintegradorfinal.model.Alquiler;

import java.util.List;

public class PagoAgregarActivity extends AppCompatActivity {

    private PagoViewModel PagoVM;
    private EditText paaEtNroPago, paaEtFecha, paaEtImporte;
    private Spinner paaSAlquiler;
    private Button paaBtnAgregar;
    private int idAlquiler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pago_agregar_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargarVistas();

        //Cargando SPINNER Alquileres, Se puede hacer un adaptador personalizado yo uso el ArrayAdapter
        PagoVM.getListaSpinnerAlquileresLD().observe(this, new Observer<List<Alquiler>>() {
            @Override
            public void onChanged(List<Alquiler> alquileres) {
                ArrayAdapter<Alquiler> alquilerArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, alquileres);
                paaSAlquiler.setAdapter(alquilerArrayAdapter);
            }
        });

        //Para Setear el ID_CORRECTAMENTE AGREGO setOnItemSelectedListener, Selecciono el Alquiler y este me devuelve su ID, el cual lo asigno a idAlquiler
        paaSAlquiler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Alquiler alquiler = (Alquiler) parent.getItemAtPosition(position);
                idAlquiler = alquiler.getIdAlquiler();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        paaBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PagoVM.alta(paaEtNroPago, paaEtFecha, paaEtImporte, idAlquiler);
            }
        });

    }

    public void cargarVistas(){
        //LLamado Al AlquilerViewModel
        PagoVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(PagoViewModel.class);
        paaEtNroPago = findViewById(R.id.etPagoNroPago);
        paaEtFecha = findViewById(R.id.etPagoFecha);
        paaEtImporte = findViewById(R.id.etPagoImporte);
        paaSAlquiler = findViewById(R.id.sPagoAlquiler);
        paaBtnAgregar = findViewById(R.id.btnPagoAgregar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    
}