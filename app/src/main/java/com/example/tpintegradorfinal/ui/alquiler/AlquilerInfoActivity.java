package com.example.tpintegradorfinal.ui.alquiler;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Alquiler;

public class AlquilerInfoActivity extends AppCompatActivity {

    private AlquilerViewModel AlquilerVM;
    private TextView aiaTvInmuebleDirecccion, aiaTvPropietarioNombreApe, aiaTvInquilinoNombreApe, aiaTvInquilinoDirecccion, aiaTvInquilinoTelefono, aiaTvInmueblePrecio, aiaTvInmuebleTipo, aiaTvInmuebleUso;
    private EditText aiaEtPrecio, aiaEtFechaInicio, aiaEtFechaFin, aiaEtInquilino, aiaInmueble;
    private ConstraintLayout expandableView;
    private Button arrowBtn;
    private CardView cardView;

    private Button aiaBtnModificar, aiaBtnEditar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alquiler_info_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargarVistas();

        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });

        AlquilerVM.getAlquilerSeleccionadoMutableLiveData().observe(this, new Observer<Alquiler>() {
            @Override
            public void onChanged(Alquiler alquiler) {
                AlquilerVM.llenarFormularioAlquiler(
                        aiaTvInmuebleDirecccion,
                        aiaTvPropietarioNombreApe,
                        aiaTvInquilinoNombreApe,
                        aiaTvInquilinoDirecccion,
                        aiaTvInquilinoTelefono,
                        aiaTvInmueblePrecio,
                        aiaTvInmuebleTipo,
                        aiaTvInmuebleUso,
                        aiaEtPrecio,
                        aiaEtFechaInicio,
                        aiaEtFechaFin,
                        aiaEtInquilino,
                        aiaInmueble,
                        alquiler);
            }
        });

        aiaBtnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlquilerVM.habilitarFormularioModificar(aiaEtPrecio, aiaEtFechaInicio, aiaEtFechaFin, aiaBtnModificar, aiaBtnEditar);
            }
        });

        aiaBtnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlquilerVM.modificar(aiaEtPrecio, aiaEtFechaInicio, aiaEtFechaFin);
            }
        });

    }

    public void cargarVistas(){
        //LLamado Al AlquilerViewModel
        AlquilerVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AlquilerViewModel.class);
        aiaTvInmuebleDirecccion = findViewById(R.id.tvAlquilerDireccionInmueble);
        aiaTvPropietarioNombreApe = findViewById(R.id.tvAlquilerPropietarioNomApe);
        aiaTvInquilinoNombreApe = findViewById(R.id.tvAlquilerInquilinoNomApe);
        aiaTvInquilinoDirecccion = findViewById(R.id.tvAlquilerInquilinoDireccion);
        aiaTvInquilinoTelefono = findViewById(R.id.tvAlquilerInquilinoTelefono);
        aiaTvInmueblePrecio = findViewById(R.id.tvAlquilerInmueblePrecio);
        aiaTvInmuebleTipo = findViewById(R.id.tvAlquilerInmuebleTipo);
        aiaTvInmuebleUso = findViewById(R.id.AlquilerInmuebleUso);

        aiaEtPrecio = findViewById(R.id.etAlquilerModificarPrecio);
        aiaEtFechaInicio = findViewById(R.id.etAlquilerModificarFechaInicio);
        aiaEtFechaFin = findViewById(R.id.etAlquilerModificarFechaFin);
        aiaEtInquilino = findViewById(R.id.etAlquilerModificarInquilino);
        aiaInmueble = findViewById(R.id.etAlquilerModificarInmueble);

        expandableView = findViewById(R.id.clExpandible);
        arrowBtn = findViewById(R.id.btnAbrir);
        cardView = findViewById(R.id.cardView);

        aiaBtnModificar = findViewById(R.id.btnAlquilerModificar);
        aiaBtnEditar = findViewById(R.id.btnAlquilerHabilitarFormulario);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}