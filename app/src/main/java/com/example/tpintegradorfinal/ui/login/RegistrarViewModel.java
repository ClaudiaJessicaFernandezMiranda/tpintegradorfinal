package com.example.tpintegradorfinal.ui.login;

import android.app.Application;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpintegradorfinal.database.DatabasePropietario;
import com.example.tpintegradorfinal.model.Propietario;

import java.util.List;

public class RegistrarViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Propietario>> listaPropietariosMutableLiveData;
    private DatabasePropietario propietarioData;
    public RegistrarViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        propietarioData = new DatabasePropietario(context);
    }

    public LiveData<List<Propietario>> getListaPropietariosMutableLiveData() {
        if (listaPropietariosMutableLiveData == null) {
            listaPropietariosMutableLiveData = new MutableLiveData<>();
        }
        return listaPropietariosMutableLiveData;
    }

    public void lvCargarPropietarios(){
        propietarioData = new DatabasePropietario(context);
        listaPropietariosMutableLiveData.setValue(propietarioData.getPropitarios());
    }

    public void registrar(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText email, EditText clave){
        if (!validarRegistroPropietario(dni, apellido, nombre, telefono, email, clave)) return;

        Propietario propietario = new Propietario();
        propietario.setDni(dni.getText().toString());
        propietario.setApellido(apellido.getText().toString());
        propietario.setNombre(nombre.getText().toString());
        propietario.setTelefono(telefono.getText().toString());
        propietario.setEmail(email.getText().toString());
        propietario.setClave(clave.getText().toString());

        if(propietarioData.altaPropietario(propietario)){
            dni.getText().clear();
            apellido.getText().clear();
            nombre.getText().clear();
            telefono.getText().clear();
            email.getText().clear();
            clave.getText().clear();
            String mesg = String.format("%s %s, Se ha registrado con exito", propietario.getApellido(), propietario.getNombre());
            Toast.makeText(context,mesg, Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"No se Guardo Nada", Toast.LENGTH_LONG).show();

    }

    public boolean validarRegistroPropietario(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText email, EditText clave) {
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
