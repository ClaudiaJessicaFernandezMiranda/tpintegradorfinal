package com.example.tpintegradorfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpintegradorfinal.model.Pago;

import java.util.ArrayList;
import java.util.List;

public class DatabasePago {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public DatabasePago(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    //COLUMNAS
    public static final String NOMBRE_TABLA = "pago";
    private static final String ID = "idPago";
    private static final String NRO_PAGO = "nro_pago";
    private static final String FECHA = "fecha";
    private static final String IMPORTE = "importe";
    private static final String ID_ALQUILER = "idAlquiler";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NRO_PAGO + " INTEGER NULL, "
            + FECHA + " TEXT NULL, "
            + IMPORTE + " REAL NULL, "
            + ID_ALQUILER + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+ ID_ALQUILER +") REFERENCES "+"alquiler"+"("+"idAlquiler"+") "
            + ");";

    public boolean altaPago(Pago pago) {
        ContentValues valores = new ContentValues();
        valores.put(ID, (Integer) null);
        valores.put(NRO_PAGO, pago.getNro_pago());
        valores.put(FECHA, pago.getFecha());
        valores.put(IMPORTE, pago.getImporte());
        valores.put(ID_ALQUILER, pago.getIdAlquiler());

        if(db.insert(NOMBRE_TABLA, null, valores) != -1) return true;
        return false;
    }

    public boolean modificacionPago(Pago pago) {
        ContentValues valores = new ContentValues();
        valores.put(NRO_PAGO, pago.getNro_pago());
        valores.put(FECHA, pago.getFecha());
        valores.put(IMPORTE, pago.getImporte());
        valores.put(ID_ALQUILER, pago.getIdAlquiler());

        String [] args = new String[]{pago.getIdPago()+""};

        if(db.update(NOMBRE_TABLA, valores,"idPago=?", args) == 1) return true;
        return false;
    }

    public List<Pago> getPagosPorIdInmueble(int idInmueble){

        List<Pago> listaPago = new ArrayList<>();
        String consulta= "SELECT * FROM pago p INNER JOIN alquiler a ON p.idAlquiler = a.idAlquiler WHERE a.idInmueble = "+idInmueble;

        Cursor resultSet = db.rawQuery(consulta, null);

        while (resultSet.moveToNext()){
            Pago pago = new Pago();
            pago.setNro_pago(resultSet.getInt(1));
            pago.setFecha(resultSet.getString(2));
            pago.setImporte(resultSet.getDouble(3));
            pago.setIdAlquiler(resultSet.getInt(4));
            listaPago.add(pago);
        }

        resultSet.close();

        return listaPago;
    }

}
