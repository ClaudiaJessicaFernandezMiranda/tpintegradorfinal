package com.example.tpintegradorfinal.ui.pago;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Pago;

import java.util.List;

public class PagosDelInmuebleActivity extends AppCompatActivity {

    private PagoViewModel PagoVM;
    private ListView pdiLvPagos;
    private PagoAdapter pagoAdapter;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos_del_inmueble_activity);
        //Cargando Menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Cargando Vistas
        cargarVistas();

        PagoVM.getListaPagosPorInmuebleMutableLiveData().observe(this, new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                pagoAdapter = new PagoAdapter(getApplicationContext(), R.layout.item_pago, pagos, getLayoutInflater());
                pdiLvPagos.setAdapter(pagoAdapter);
            }
        });
    }

    public void cargarVistas(){
        //LLamado Al PagosViewModel
        PagoVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(PagoViewModel.class);
        pdiLvPagos = findViewById(R.id.lvPagosDelInmueble);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pago, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_agregar_pago:
                intent = new Intent(this, PagoAgregarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}