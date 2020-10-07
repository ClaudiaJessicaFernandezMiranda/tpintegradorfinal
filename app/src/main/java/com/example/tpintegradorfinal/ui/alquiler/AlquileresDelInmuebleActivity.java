package com.example.tpintegradorfinal.ui.alquiler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpintegradorfinal.R;
import com.example.tpintegradorfinal.model.Alquiler;

import java.util.List;

public class AlquileresDelInmuebleActivity extends AppCompatActivity {

    private AlquilerViewModel AlquilerVM;
    private ListView iiALvAlquiler;
    private AlquierAdapter alquilerAdapter;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alquileres_del_inmueble_activity);
        //Cargando Menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Cargando Vistas
        cargarVistas();

        AlquilerVM.getListaAlquileresPorInmuebleMutableLiveData().observe(this, new Observer<List<Alquiler>>() {
            @Override
            public void onChanged(List<Alquiler> alquileres) {
                alquilerAdapter = new AlquierAdapter(getApplicationContext(), R.layout.item_alquiler, alquileres, getLayoutInflater());
                iiALvAlquiler.setAdapter(alquilerAdapter);
            }
        });

        iiALvAlquiler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(AlquilerViewModel.iniciarActividadAlquiler(getApplicationContext(), position, alquilerAdapter));
            }
        });

    }

    public void cargarVistas(){
        //LLamado Al AlquilerViewModel
        AlquilerVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AlquilerViewModel.class);
        iiALvAlquiler = findViewById(R.id.lvAlquileresDelInmueble);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alquiler, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_agregar_alquiler:
                intent = new Intent(this, AlquilerAgregarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}