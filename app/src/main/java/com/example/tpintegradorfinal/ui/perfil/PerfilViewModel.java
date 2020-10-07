package com.example.tpintegradorfinal.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpintegradorfinal.database.DatabasePropietario;
import com.example.tpintegradorfinal.model.Propietario;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private Propietario propietario;
    private DatabasePropietario propietarioData;
    private MutableLiveData<Propietario> logeado;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        propietarioData = new DatabasePropietario(context);
    }

    public LiveData<Propietario> getlogeado() {
        if (logeado == null) {
            logeado = new MutableLiveData<>();
        }
        return logeado;
    }

    public void cargarLogeado(){
        logeado.setValue(propietarioData.logeado);
    }

    public void llenarFormulario(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText email, EditText clave, Propietario propietario) {

        dni.setText(propietario.getDni());
        apellido.setText(propietario.getApellido());
        nombre.setText(propietario.getNombre());
        telefono.setText(propietario.getTelefono());
        email.setText(propietario.getEmail());
        clave.setText(propietario.getClave());
    }

    public void modificar(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText email, EditText clave){
        if (!validarModificacionPropietario(dni, apellido, nombre, telefono, email, clave)) return;
        propietario = new Propietario(
                DatabasePropietario.logeado.getIdPropietario(),
                dni.getText().toString(),
                apellido.getText().toString(),
                nombre.getText().toString(),
                telefono.getText().toString(),
                email.getText().toString(),
                clave.getText().toString()
        );

        if(propietarioData.modificacionPropietario(propietario)){
            dni.getText().clear();
            apellido.getText().clear();
            nombre.getText().clear();
            telefono.getText().clear();
            email.getText().clear();
            clave.getText().clear();
            Toast.makeText(context,"Perfil modificado correctamente", Toast.LENGTH_LONG).show();
        }else Toast.makeText(context,"Error al modificar Propietario", Toast.LENGTH_LONG).show();

    }

    public void editar(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText email, EditText clave, Button modificar, Button editar){
        dni.setEnabled(true);
        apellido.setEnabled(true);
        nombre.setEnabled(true);
        telefono.setEnabled(true);
        email.setEnabled(true);
        clave.setEnabled(true);
        email.setEnabled(true);
        modificar.setEnabled(true);
        modificar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.GONE);
    }

    public boolean validarModificacionPropietario(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText email, EditText clave) {
        boolean esValido = true;

        String vDni = dni.getText().toString();
        String vApellido = apellido.getText().toString();
        String vNombre = nombre.getText().toString();
        String vTelefono = telefono.getText().toString();
        String vEmail = email.getText().toString();
        String vClave = clave.getText().toString();

        if (!vDni.matches("[0-9 ]{8,15}")) {
            dni.setError("Solo números y mínimo 8 caracteres");
            esValido = false;
        } else {
            dni.setError(null);
        }
        if (!vApellido.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]{3,30}")) {
            apellido.setError("Solo letras y mínimo 3 caracteres");
            esValido = false;
        } else {
            apellido.setError(null);
        }
        if (!vNombre.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]{3,30}")) {
            nombre.setError("Solo letras y mínimo 3 caracteres");
            esValido = false;
        } else {
            nombre.setError(null);
        }
        if (!vTelefono.matches("[0-9 ]{10,20}")) {
            telefono.setError("Solo números y mínimo 10 caracteres");
            esValido = false;
        } else {
            telefono.setError(null);
        }
        if (vEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(vEmail).matches()) {
            email.setError("Introduzca una dirección de correo electrónico válida");
            esValido = false;
        } else {
            email.setError(null);
        }
        if (vClave.isEmpty() || vClave.length() < 4 || vClave.length() > 10) {
            clave.setError("Entre 4 y 10 caracteres");
            esValido = false;
        } else {
            clave.setError(null);
        }

        return esValido;
    }



}
