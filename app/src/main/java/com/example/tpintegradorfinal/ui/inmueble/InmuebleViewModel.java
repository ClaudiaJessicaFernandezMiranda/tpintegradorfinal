package com.example.tpintegradorfinal.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpintegradorfinal.database.DatabaseInmueble;
import com.example.tpintegradorfinal.database.DatabasePropietario;
import com.example.tpintegradorfinal.model.Inmueble;

import java.util.List;

public class InmuebleViewModel extends AndroidViewModel {
    private static Inmueble lvInmuebleSeleccionado = new Inmueble();
    private Context context;
    private DatabaseInmueble databaseInmueble;
    private MutableLiveData<List<Inmueble>> listaInmueblesMutableLiveData;
    private MutableLiveData<Inmueble> inmuebleSeleccionadoMutableLiveData;
    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        databaseInmueble = new DatabaseInmueble(context);
    }

    public static Intent iniciarActividad(FragmentActivity activity, int posicion, InmuebleAdapter inmuebleAdapter) {
        lvInmuebleSeleccionado = inmuebleAdapter.getItem(posicion);
        Intent intent = new Intent(activity, InmuebleInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public LiveData<Inmueble> getInmuebleSeleccionadoMutableLiveData() {
        if (inmuebleSeleccionadoMutableLiveData == null) {
            inmuebleSeleccionadoMutableLiveData = new MutableLiveData<>();
            cargarInmuebleSeleccionado();
        }
        return inmuebleSeleccionadoMutableLiveData;
    }

    public LiveData<List<Inmueble>> getListaInmueblesMutableLiveData() {
        if (listaInmueblesMutableLiveData == null) {
            listaInmueblesMutableLiveData = new MutableLiveData<>();
            lvCargarInmuebles();
        }
        return listaInmueblesMutableLiveData;
    }

    public void cargarInmuebleSeleccionado(){
        inmuebleSeleccionadoMutableLiveData.setValue(lvInmuebleSeleccionado);
    }

    public void lvCargarInmuebles(){
        List<Inmueble> inmuebles = databaseInmueble.getInmuebles(DatabasePropietario.logeado.getIdPropietario());
        listaInmueblesMutableLiveData.setValue(inmuebles);

    }

    public void alta(EditText direccion, EditText ambientes, Spinner tipo, Spinner uso, EditText precio, RadioButton disponible, RadioButton noDisponible){
        if (!validarInmueble(direccion, ambientes, tipo, uso, precio, disponible, noDisponible, DatabasePropietario.logeado.getIdPropietario())) return;

        Inmueble inmueble = new Inmueble();

        inmueble.setDireccion(direccion.getText().toString());
        inmueble.setAmbientes(Integer.parseInt(ambientes.getText().toString()));
        inmueble.setTipo(tipo.getSelectedItem().toString());
        inmueble.setUso(uso.getSelectedItem().toString());
        inmueble.setPrecio(Double.parseDouble(precio.getText().toString()));
        if(disponible.isChecked()) inmueble.setDisponible(1);
        else inmueble.setDisponible(0);
        inmueble.setIdPropietario(DatabasePropietario.logeado.getIdPropietario());

        if(databaseInmueble.altaInmueble(inmueble)){
            direccion.getText().clear();
            ambientes.getText().clear();
            tipo.setSelection(0);
            uso.setSelection(0);
            precio.getText().clear();
            disponible.setChecked(false);
            noDisponible.setChecked(false);
            Toast.makeText(context,"Inmueble agregado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al agregar Inmueble", Toast.LENGTH_LONG).show();

    }

    public void modificar(EditText direccion, EditText ambientes, Spinner tipo, Spinner uso, EditText precio, RadioButton disponible, RadioButton noDisponible){
        if (!validarInmueble(direccion, ambientes, tipo, uso, precio, disponible, noDisponible, DatabasePropietario.logeado.getIdPropietario())) return;

        Inmueble inmueble = new Inmueble();
        inmueble.setIdInmueble(lvInmuebleSeleccionado.getIdInmueble());
        inmueble.setDireccion(direccion.getText().toString());
        inmueble.setAmbientes(Integer.parseInt(ambientes.getText().toString()));
        inmueble.setTipo(tipo.getSelectedItem().toString());
        inmueble.setUso(uso.getSelectedItem().toString());
        inmueble.setPrecio(Double.parseDouble(precio.getText().toString()));
        if(disponible.isChecked()) inmueble.setDisponible(1);
        else inmueble.setDisponible(0);
        inmueble.setIdPropietario(DatabasePropietario.logeado.getIdPropietario());

        if(databaseInmueble.modificacionInmueble(inmueble)){
            direccion.getText().clear();
            ambientes.getText().clear();
            tipo.setSelection(0);
            uso.setSelection(0);
            precio.getText().clear();
            disponible.setChecked(false);
            noDisponible.setChecked(false);
            Toast.makeText(context,"Inmueble modificado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al modificar Inmueble", Toast.LENGTH_LONG).show();

    }

    public void llenarFormularioInmueble(EditText direccion, EditText ambientes, Spinner tipo, Spinner uso, EditText precio, RadioButton disponible, RadioButton noDisponible, Inmueble inmueble) {

        direccion.setText(inmueble.getDireccion());
        ambientes.setText(inmueble.getDisponible()+"");
        for(int i = 0; i < tipo.getCount(); i++){
           if(tipo.getItemAtPosition(i).toString().equals(inmueble.getTipo())){
               tipo.setSelection(i);
           }
        }
        for(int i = 0; i < uso.getCount(); i++){
            if(uso.getItemAtPosition(i).toString().equals(inmueble.getUso())){
                uso.setSelection(i);
            }
        }
        if(inmueble.getDisponible() == 1){
            disponible.setChecked(true);
        }else noDisponible.setChecked(true);

        precio.setText(inmueble.getPrecio()+"");

    }

    public boolean validarInmueble(EditText direccion, EditText ambientes, Spinner tipo, Spinner uso, EditText precio, RadioButton disponible, RadioButton noDisponible, int idPropietario) {
        boolean esValido = true;

        String vDireccion = direccion.getText().toString();
        String vAmbientes = ambientes.getText().toString();
        String vTipo = tipo.getSelectedItem().toString();
        String vUso = uso.getSelectedItem().toString();
        String vPrecio = precio.getText().toString();

        if (!vDireccion.matches("[a-zA-ZÀ-ÿ\\\\u00f1\\\\u00d1\\\\ 0-9]{3,30}")) {
            direccion.setError("Letras, numeros y mínimo 3 caracteres");
            esValido = false;
        } else {
            direccion.setError(null);
        }
        if (!vAmbientes.matches("[0-9 ]{1,4}")) {
            ambientes.setError("Solo numeros y maximo 4 caracteres");
            esValido = false;
        } else {
            ambientes.setError(null);
        }
        if (vTipo.equals("Tipo de inmueble")) {
            ((TextView)tipo.getChildAt(0)).setError("");
            ((TextView)tipo.getChildAt(0)).setTextColor(Color.RED);
            ((TextView)tipo.getChildAt(0)).setText("Selecciona Tipo");
            esValido = false;
        } else {
            ((TextView)tipo.getChildAt(0)).setError(null);
        }
        if (vUso.equals("Uso del inmueble")) {
            ((TextView)uso.getChildAt(0)).setError("");
            ((TextView)uso.getChildAt(0)).setTextColor(Color.RED);
            ((TextView)uso.getChildAt(0)).setText("Selecciona Uso");
            esValido = false;
        } else {
            ((TextView)uso.getChildAt(0)).setError(null);
        }
        if (!vPrecio.matches("[0-9]*[.,]?[0-9]+")) {
            precio.setError("Solo números simples o con decimales");
            esValido = false;
        } else {
            precio.setError(null);
        }

        return esValido;
    }

    public void habilitarFormularioModificar(EditText direccion, EditText ambientes, Spinner tipo, Spinner uso, EditText precio, RadioButton disponible, RadioButton noDisponible, Button modificar, Button editar){
        direccion.setEnabled(true);
        ambientes.setEnabled(true);
        tipo.setEnabled(true);
        uso.setEnabled(true);
        precio.setEnabled(true);
        disponible.setEnabled(true);
        noDisponible.setEnabled(true);
        modificar.setEnabled(true);
        modificar.setVisibility(View.VISIBLE);
        modificar.setText("Modificar");
        editar.setVisibility(View.GONE);
    }

    public void habilitarFormulacioAgregar(EditText direccion, EditText ambientes, Spinner tipo, Spinner uso, EditText precio, RadioButton disponible, RadioButton noDisponible, Button agregar, Button editar){
        direccion.setEnabled(true);
        ambientes.setEnabled(true);
        tipo.setEnabled(true);
        uso.setEnabled(true);
        precio.setEnabled(true);
        disponible.setEnabled(true);
        noDisponible.setEnabled(true);
        agregar.setText("Agregar");
        agregar.setVisibility(View.VISIBLE);
        agregar.setEnabled(true);
        editar.setVisibility(View.GONE);

    }

}
