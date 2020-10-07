package com.example.tpintegradorfinal.ui.inquilino;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;

public class InquilinoAgregarActivity extends AppCompatActivity {

    private InquilinoViewModel InquilinoVM;
    private EditText iifEtDni, iifEtApellido, iifEtNombre, iifEtDireccion, iifEtTelefono;
    private Button iifBtnAgregar, iifBtnEditar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquilino_info_agregar_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargarVistas();

        iifBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InquilinoVM.alta(iifEtDni, iifEtApellido, iifEtNombre, iifEtDireccion, iifEtTelefono);
            }
        });

        InquilinoVM.habilitarFormulacioAgregar(iifEtDni, iifEtApellido, iifEtNombre, iifEtDireccion, iifEtTelefono, iifBtnAgregar, iifBtnEditar);
    }

    public void cargarVistas(){
        //LLamado Al InquilinoViewModel
        InquilinoVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(InquilinoViewModel.class);
        iifEtDni = findViewById(R.id.etInquilinoDni);
        iifEtApellido = findViewById(R.id.etInquilinoApellido);
        iifEtNombre = findViewById(R.id.etInquilinoNombre);
        iifEtDireccion = findViewById(R.id.etInquilinoDireccion);
        iifEtTelefono = findViewById(R.id.etInquilinoTelefono);
        iifBtnAgregar = findViewById(R.id.btnInquilinoAgregarModificar);
        iifBtnEditar = findViewById(R.id.btnInquilinoHabilitarFormulario);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    
}