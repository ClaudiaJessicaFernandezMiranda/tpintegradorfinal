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

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private DatabasePropietario propietarioData;
    private MutableLiveData<Boolean> iniciarSesion;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        propietarioData = new DatabasePropietario(context);
    }

    public LiveData<Boolean> getIniciarSesion() {
        if (iniciarSesion == null) {
            iniciarSesion = new MutableLiveData<>();
        }
        return iniciarSesion;
    }

    public void login(EditText email, EditText clave){
        if (!validarLoginPropietario(email, clave)) return;

        if (propietarioData.comprobarRegistro(email.getText().toString())){
               if(propietarioData.login(email.getText().toString(), clave.getText().toString())){
                   Toast.makeText(context, "Bienvenido " + propietarioData.logeado.getNombre() +" "+ propietarioData.logeado.getApellido(), Toast.LENGTH_LONG).show();
                   iniciarSesion.setValue(true);
               }
               else {
                   Toast.makeText(context, "Tus credenciales no coinciden con una cuenta en nuestro sistema", Toast.LENGTH_LONG).show();
               }
        }
        else email.setError("El correo electrónico no coincide con una cuenta en nuestro sistema");
    }

    public boolean validarLoginPropietario(EditText email, EditText clave) {
        boolean esValido = true;

        String vEmail = email.getText().toString();
        String vClave = clave.getText().toString();

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
