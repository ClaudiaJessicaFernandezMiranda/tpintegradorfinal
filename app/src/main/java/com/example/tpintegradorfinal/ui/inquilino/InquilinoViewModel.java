package com.example.tpintegradorfinal.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpintegradorfinal.database.DatabaseInquilino;
import com.example.tpintegradorfinal.model.Inquilino;

import java.util.List;

public class InquilinoViewModel extends AndroidViewModel {
    private static Inquilino lvInquilinoSelecionado = new Inquilino();
    private Context context;
    private Inquilino inquilino;
    private DatabaseInquilino databaseInquilino;
    private MutableLiveData<List<Inquilino>> listaInquilinosMutableLiveData;
    private MutableLiveData<Inquilino> inmuebleSeleccionadoMutableLiveData;
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        databaseInquilino = new DatabaseInquilino(context);
    }

    public static Intent iniciarActividad(FragmentActivity activity, int posicion, InquilinoAdapter inquilinoAdapter) {
        lvInquilinoSelecionado = inquilinoAdapter.getItem(posicion);
        Intent intent= new Intent(activity, InquilinoInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public LiveData<Inquilino> getInquilinoSeleccionadoMutableLiveData() {
        if (inmuebleSeleccionadoMutableLiveData == null) {
            inmuebleSeleccionadoMutableLiveData = new MutableLiveData<>();
            cargarInmuebleSeleccionado();
        }
        return inmuebleSeleccionadoMutableLiveData;
    }

    public LiveData<List<Inquilino>> getListaInquilinosMutableLiveData() {
        if (listaInquilinosMutableLiveData == null) {
            listaInquilinosMutableLiveData = new MutableLiveData<>();
            lvCargarInquilinos();
        }
        return listaInquilinosMutableLiveData;
    }

    public void cargarInmuebleSeleccionado(){
        inmuebleSeleccionadoMutableLiveData.setValue(lvInquilinoSelecionado);
    }

    public void lvCargarInquilinos(){
        listaInquilinosMutableLiveData.setValue(databaseInquilino.getInquilinos());
    }

    public void alta(EditText dni, EditText apellido, EditText nombre, EditText direccion, EditText telefono){
        if (!validarInquilino(dni, apellido, nombre, direccion, telefono)) return;

        inquilino = new Inquilino(
                0,
                dni.getText().toString(),
                apellido.getText().toString(),
                nombre.getText().toString(),
                direccion.getText().toString(),
                telefono.getText().toString()
        );

        if(databaseInquilino.altaInquilino(inquilino)){
            dni.getText().clear();
            apellido.getText().clear();
            nombre.getText().clear();
            direccion.getText().clear();
            telefono.getText().clear();
            Toast.makeText(context,"Inquilino agregado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al agregar Inquilino", Toast.LENGTH_LONG).show();

    }

    public void modificar(EditText dni, EditText apellido, EditText nombre, EditText direccion, EditText telefono){
        if (!validarInquilino(dni, apellido, nombre, direccion, telefono)) return;

        inquilino = new Inquilino(
                lvInquilinoSelecionado.getIdInquilino(),
                dni.getText().toString(),
                apellido.getText().toString(),
                nombre.getText().toString(),
                direccion.getText().toString(),
                telefono.getText().toString()
        );

        if(databaseInquilino.modificacionInquilino(inquilino)){
            dni.getText().clear();
            apellido.getText().clear();
            nombre.getText().clear();
            direccion.getText().clear();
            telefono.getText().clear();
            Toast.makeText(context,"Inquilino modificado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al modificar Inquilino", Toast.LENGTH_LONG).show();

    }

    public void llenarFormularioInquilino(EditText dni, EditText apellido, EditText nombre, EditText direccion, EditText telefono, Inquilino inquilino) {
        dni.setText(inquilino.getDni());
        apellido.setText(inquilino.getApellido());
        nombre.setText(inquilino.getNombre());
        direccion.setText(inquilino.getDireccion());
        telefono.setText(inquilino.getTelefono());
    }

    public boolean validarInquilino(EditText dni, EditText apellido, EditText nombre, EditText direccion, EditText telefono) {
        boolean validar = true;
        String vDni = dni.getText().toString();
        String vApellido = apellido.getText().toString();
        String vNombre = nombre.getText().toString();
        String vDireccion = direccion.getText().toString();
        String vTelefono = telefono.getText().toString();

        if (!vDni.matches("[0-9 ]{8,15}")) {
            dni.setError("Solo números y mínimo 8 caracteres");
            validar = false;
        } else dni.setError(null);

        if (!vApellido.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]{3,30}")) {
            apellido.setError("Solo letras y mínimo 3 caracteres");
            validar = false;
        } else apellido.setError(null);

        if (!vNombre.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]{3,30}")) {
            nombre.setError("Solo letras y mínimo 3 caracteres");
            validar = false;
        } else nombre.setError(null);

        if (!vDireccion.matches("[a-zA-ZÀ-ÿ\\\\u00f1\\\\u00d1\\\\0-9 ]{3,30}")) {
            direccion.setError("Letras, numeros y mínimo 3 caracteres");
            validar = false;
        } else direccion.setError(null);

        if (!vTelefono.matches("[0-9 ]{10,20}")) {
            telefono.setError("Solo números y mínimo 10 caracteres");
            validar = false;
        } else telefono.setError(null);

        return validar;
    }

    public void habilitarFormularioModificar(EditText dni, EditText apellido, EditText nombre, EditText direccion, EditText telefono, Button modificar, Button editar){
        dni.setEnabled(true);
        apellido.setEnabled(true);
        nombre.setEnabled(true);
        direccion.setEnabled(true);
        telefono.setEnabled(true);
        modificar.setEnabled(true);
        modificar.setVisibility(View.VISIBLE);
        modificar.setText("Modificar");
        editar.setVisibility(View.GONE);
    }

    public void habilitarFormulacioAgregar(EditText dni, EditText apellido, EditText nombre, EditText direccion, EditText telefono, Button agregar, Button editar){
        dni.setEnabled(true);
        apellido.setEnabled(true);
        nombre.setEnabled(true);
        direccion.setEnabled(true);
        telefono.setEnabled(true);
        agregar.setText("Agregar");
        agregar.setVisibility(View.VISIBLE);
        agregar.setEnabled(true);
        editar.setVisibility(View.GONE);

    }

}
