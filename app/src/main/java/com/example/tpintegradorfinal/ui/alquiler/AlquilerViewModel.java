package com.example.tpintegradorfinal.ui.alquiler;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpintegradorfinal.database.DatabaseAlquiler;
import com.example.tpintegradorfinal.database.DatabaseInmueble;
import com.example.tpintegradorfinal.database.DatabaseInquilino;
import com.example.tpintegradorfinal.database.DatabasePropietario;
import com.example.tpintegradorfinal.model.Alquiler;
import com.example.tpintegradorfinal.model.Inmueble;
import com.example.tpintegradorfinal.model.Inquilino;
import com.example.tpintegradorfinal.ui.inmueble.InmuebleAdapter;

import java.util.List;

public class AlquilerViewModel extends AndroidViewModel {
    private static Alquiler lvAlquilerSelecionado;
    private static Inmueble lvInmuebleSelecionado = new Inmueble();
    private Context context;
    private DatabaseAlquiler databaseAlquiler;
    private DatabaseInquilino databaseInquilino;
    private DatabaseInmueble databaseInmueble;
    private MutableLiveData<List<Inquilino>> listaInquilinosMutableLiveData;
    private MutableLiveData<List<Inmueble>> listaInmueblesMutableLiveData;
    private MutableLiveData<Alquiler> alquilerSeleccionadoMutableLiveData;

    //MODIFICANDO
    private MutableLiveData<List<Alquiler>> listaAlquileresPorInmuebleMutableLiveData;
    //MODIFICANDO

    public AlquilerViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        databaseAlquiler = new DatabaseAlquiler(context);
        databaseInquilino = new DatabaseInquilino(context);
        databaseInmueble = new DatabaseInmueble(context);
    }

    public static Intent iniciarActividadAlquiler(Context activity, int posicion, AlquierAdapter alquierAdapter) {
        lvAlquilerSelecionado = alquierAdapter.getItem(posicion);
        Intent intent = new Intent(activity, AlquilerInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static Intent iniciarActividadInmueble(FragmentActivity activity, int posicion, InmuebleAdapter inmuebleAdapter) {
        lvInmuebleSelecionado = inmuebleAdapter.getItem(posicion);
        Intent intent = new Intent(activity, AlquileresDelInmuebleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    //MODIFICANDO
    public LiveData<Alquiler> getAlquilerSeleccionadoMutableLiveData() {
        if (alquilerSeleccionadoMutableLiveData == null) {
            alquilerSeleccionadoMutableLiveData = new MutableLiveData<>();
            cargarAlquilerSeleccionado();
        }
        return alquilerSeleccionadoMutableLiveData;
    }

    //MODIFICANDO

    //MODIFICANDO
    public LiveData<List<Alquiler>> getListaAlquileresPorInmuebleMutableLiveData() {
        if (listaAlquileresPorInmuebleMutableLiveData == null) {
            listaAlquileresPorInmuebleMutableLiveData = new MutableLiveData<>();
            lvCargarAlquileresPorInmueble();
        }
        return listaAlquileresPorInmuebleMutableLiveData;
    }

    public void lvCargarAlquileresPorInmueble(){
        listaAlquileresPorInmuebleMutableLiveData.setValue(databaseAlquiler.getAlquileresPorIdInmueble(lvInmuebleSelecionado.getIdInmueble()));
    }
    //MODIFICANDO

    public LiveData<List<Inquilino>> getListaInquilinosMutableLiveData() {
        if (listaInquilinosMutableLiveData == null) {
            listaInquilinosMutableLiveData = new MutableLiveData<>();
            spinnerCargarInquilinos();
        }
        return listaInquilinosMutableLiveData;
    }

    public LiveData<List<Inmueble>> getListaInmueblesMutableLiveData() {
        if (listaInmueblesMutableLiveData == null) {
            listaInmueblesMutableLiveData = new MutableLiveData<>();
            spinnerCargarInmuebles();
        }
        return listaInmueblesMutableLiveData;
    }
    //MODIFICANDO
    public void cargarAlquilerSeleccionado(){
        alquilerSeleccionadoMutableLiveData.setValue(databaseAlquiler.getAlquilerSeleccionado(lvAlquilerSelecionado.getIdInquilino(), lvAlquilerSelecionado.getIdInmueble()));

    }
    //MODIFICANDO
    private void spinnerCargarInquilinos() {
        listaInquilinosMutableLiveData.setValue(databaseInquilino.getInquilinos());
    }

    private void spinnerCargarInmuebles() {
        listaInmueblesMutableLiveData.setValue(databaseInmueble.getInmuebles(DatabasePropietario.logeado.getIdPropietario()));
    }

    public void alta(EditText precio, EditText fechaInicio, EditText fechaFin, int idInquilino, int idInmueble){
        if (!validarAlquiler(precio, fechaInicio, fechaFin, idInquilino, idInmueble)) return;

        Alquiler alquiler = new Alquiler();
        alquiler.setPrecio(Double.parseDouble(precio.getText().toString()));
        alquiler.setFecha_inicio(fechaInicio.getText().toString());
        alquiler.setFecha_fin(fechaFin.getText().toString());
        alquiler.setIdInquilino(idInquilino);
        alquiler.setIdInmueble(idInmueble);

        if(databaseAlquiler.altaAlquiler(alquiler)){
            precio.getText().clear();
            fechaInicio.getText().clear();
            fechaFin.getText().clear();
            Toast.makeText(context,"Alquiler agregado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al agregar Alquiler", Toast.LENGTH_LONG).show();

    }

    public void modificar(EditText precio, EditText fechaInicio, EditText fechaFin){
        if (!validarAlquiler(precio, fechaInicio, fechaFin, lvAlquilerSelecionado.getIdInquilino(), lvAlquilerSelecionado.getIdInmueble())) return;

        Alquiler alquiler = new Alquiler();
        alquiler.setIdAlquiler(lvAlquilerSelecionado.getIdAlquiler());
        alquiler.setPrecio(Double.parseDouble(precio.getText().toString()));
        alquiler.setFecha_inicio(fechaInicio.getText().toString());
        alquiler.setFecha_fin(fechaFin.getText().toString());
        alquiler.setIdInquilino(lvAlquilerSelecionado.getIdInquilino());
        alquiler.setIdInmueble(lvAlquilerSelecionado.getIdInmueble());

        if(databaseAlquiler.modificacionAlquiler(alquiler)){
            precio.getText().clear();
            fechaInicio.getText().clear();
            fechaFin.getText().clear();
            Toast.makeText(context,"Alquiler modificado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al modificar Alquiler", Toast.LENGTH_LONG).show();

    }

    public void llenarFormularioAlquiler(TextView direccionInmueble,
                                         TextView nombreApePropietario,
                                         TextView nombreApeInquilino,
                                         TextView direccionInquilino,
                                         TextView telefono,
                                         TextView precio,
                                         TextView tipo,
                                         TextView uso,
                                         EditText precioAlquiler,
                                         EditText fechaInicio,
                                         EditText fechaFin,
                                         EditText inquilino,
                                         EditText inmueble,
                                         Alquiler alquiler) {
        direccionInmueble.setText(alquiler.getInmueble().getDireccion());
        nombreApePropietario.setText(String.format("%s %s", alquiler.getPropietario().getNombre(), alquiler.getPropietario().getApellido()));
        nombreApeInquilino.setText(String.format("%s %s", alquiler.getInquilino().getNombre(), alquiler.getInquilino().getApellido()));
        direccionInquilino.setText(alquiler.getInquilino().getDireccion());
        telefono.setText(alquiler.getInquilino().getTelefono());
        precio.setText(String.format("%s", alquiler.getInmueble().getPrecio()));
        tipo.setText(alquiler.getInmueble().getTipo());
        uso.setText(alquiler.getInmueble().getUso());
        precioAlquiler.setText(String.format("%s", alquiler.getPrecio()));
        fechaInicio.setText(alquiler.getFecha_inicio());
        fechaFin.setText(alquiler.getFecha_fin());
        inquilino.setText(String.format("%s %s", alquiler.getInquilino().getNombre(), alquiler.getInquilino().getApellido()));
        inmueble.setText(alquiler.getInmueble().getDireccion());

    }

    public boolean validarAlquiler(EditText precio, EditText fechaInicio, EditText fechaFin, int idInquilino, int idInmueble) {
        boolean validar = true;
        String vPrecio = precio.getText().toString();
        String vFechaInicio = fechaInicio.getText().toString();
        String vFechaFin = fechaFin.getText().toString();
        int vInquilino = idInquilino;
        int vInmueble = idInmueble;

        if (!vPrecio.matches("[0-9]*[.,]?[0-9]+")) {
            precio.setError("Solo números simples o con decimales");
            validar = false;
        } else precio.setError(null);

        if (!vFechaInicio.matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d")) {
            fechaInicio.setError("Ingrese fecha valida entre hoy al 31-12-2099");
            validar = false;
        } else fechaInicio.setError(null);

        if (!vFechaFin.matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d")) {
            fechaFin.setError("Ingrese fecha valida entre hoy al 31-12-2099");
            validar = false;
        } else fechaFin.setError(null);

        return validar;
    }

    public void habilitarFormularioModificar(EditText precio, EditText fechaInicio, EditText fechaFin, Button modificar, Button editar){
        precio.setEnabled(true);
        fechaInicio.setEnabled(true);
        fechaFin.setEnabled(true);
        modificar.setEnabled(true);
        modificar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.GONE);
    }

}
