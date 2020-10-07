package com.example.tpintegradorfinal.ui.pago;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpintegradorfinal.database.DatabaseAlquiler;
import com.example.tpintegradorfinal.database.DatabasePago;
import com.example.tpintegradorfinal.model.Alquiler;
import com.example.tpintegradorfinal.model.Inmueble;
import com.example.tpintegradorfinal.model.Pago;
import com.example.tpintegradorfinal.ui.inmueble.InmuebleAdapter;

import java.util.List;

public class PagoViewModel extends AndroidViewModel {
    private Context context;
    private static Alquiler lvAlquilerSelecionado;
    private static Inmueble lvInmuebleSelecionado = new Inmueble();
    private DatabaseAlquiler databaseAlquiler;
    private DatabasePago databasePago;
    private MutableLiveData<List<Alquiler>> listaSpinnerAlquileresMLD;

    private MutableLiveData<List<Pago>> listaPagosPorInmuebleMLD;


    public PagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        databaseAlquiler = new DatabaseAlquiler(context);
        databasePago = new DatabasePago(context);
    }

    public static Intent iniciarActividadInmueble(FragmentActivity activity, int posicion, InmuebleAdapter inmuebleAdapter) {
        lvInmuebleSelecionado = inmuebleAdapter.getItem(posicion);
        Intent intent = new Intent(activity, PagosDelInmuebleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public LiveData<List<Pago>> getListaPagosPorInmuebleMutableLiveData() {
        if (listaPagosPorInmuebleMLD == null) {
            listaPagosPorInmuebleMLD = new MutableLiveData<>();
            lvCargarPagosPorInmueble();
        }
        return listaPagosPorInmuebleMLD;
    }

    public void lvCargarPagosPorInmueble(){
        listaPagosPorInmuebleMLD.setValue(databasePago.getPagosPorIdInmueble(lvInmuebleSelecionado.getIdInmueble()));
    }

    //asdsadsaasdsasa
    public LiveData<List<Alquiler>> getListaSpinnerAlquileresLD() {
        if (listaSpinnerAlquileresMLD == null) {
            listaSpinnerAlquileresMLD = new MutableLiveData<>();
            spinnerAlquileresPorInmueble();
        }
        return listaSpinnerAlquileresMLD;
    }

    public void spinnerAlquileresPorInmueble(){
        listaSpinnerAlquileresMLD.setValue(databaseAlquiler.getAlquileresPorIdInmueble(lvInmuebleSelecionado.getIdInmueble()));
    }

    public void alta(EditText nroPago, EditText fecha, EditText importe, int idAlquiler){
        if (!validarAlquiler(nroPago, fecha, importe, idAlquiler)) return;

        Pago pago = new Pago();
        pago.setNro_pago(Integer.parseInt(nroPago.getText().toString()));
        pago.setFecha(fecha.getText().toString());
        pago.setImporte(Double.parseDouble(importe.getText().toString()));
        pago.setIdAlquiler(idAlquiler);

        if(databasePago.altaPago(pago)){
            nroPago.getText().clear();
            fecha.getText().clear();
            importe.getText().clear();
            Toast.makeText(context,"Pago agregado correctamente", Toast.LENGTH_LONG).show();

        }else Toast.makeText(context,"Error al agregar Pago", Toast.LENGTH_LONG).show();

    }

    public boolean validarAlquiler(EditText nroPago, EditText fecha, EditText importe, int idAlquiler) {
        boolean validar = true;
        String vNroPago = nroPago.getText().toString();
        String vFecha = fecha.getText().toString();
        String vImporte = importe.getText().toString();
        int vAlquiler = idAlquiler;

        if (!vNroPago.matches("[0-9 ]{1,15}")) {
            nroPago.setError("Solo números y mínimo 1 caracter");
            validar = false;
        } else nroPago.setError(null);

        if (!vFecha.matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d")) {
            fecha.setError("Ingrese fecha valida entre hoy al 31-12-2099");
            validar = false;
        } else fecha.setError(null);

        if (!vImporte.matches("[0-9]*[.,]?[0-9]+")) {
            importe.setError("Solo números simples o con decimales");
            validar = false;
        } else importe.setError(null);

        return validar;
    }

}
