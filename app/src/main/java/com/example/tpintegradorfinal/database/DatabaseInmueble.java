package com.example.tpintegradorfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpintegradorfinal.model.Inmueble;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInmueble {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public DatabaseInmueble(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    //COLUMNAS
    public static final String NOMBRE_TABLA = "inmueble";
    private static final String ID = "idInmueble";
    private static final String DIRECCION = "direccion";
    private static final String AMBIENTES = "ambientes";
    private static final String TIPO = "tipo";
    private static final String USO = "uso";
    private static final String PRECIO = "precio";
    private static final String DISPONIBLE = "disponible";
    private static final String ID_PROPIETARIO = "idPropietario";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DIRECCION + " TEXT NULL, "
            + AMBIENTES + " INTEGER NULL, "
            + TIPO + " TEXT NULL, "
            + USO + " TEXT NULL, "
            + PRECIO + " REAL NULL, "
            + DISPONIBLE + " INTEGER DEFAULT 0, "
            + ID_PROPIETARIO + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+ID_PROPIETARIO+") REFERENCES "+"propietario"+"("+"idPropietario"+")"
            + ");";

    public boolean altaInmueble(Inmueble inmueble) {
        ContentValues valores = new ContentValues();
        valores.put(ID, (Integer) null);
        valores.put(DIRECCION, inmueble.getDireccion());
        valores.put(AMBIENTES, inmueble.getAmbientes());
        valores.put(TIPO, inmueble.getTipo());
        valores.put(USO, inmueble.getUso());
        valores.put(PRECIO, inmueble.getPrecio());
        valores.put(DISPONIBLE, inmueble.getDisponible());
        valores.put(ID_PROPIETARIO, inmueble.getIdPropietario());

        if(db.insert(NOMBRE_TABLA, null, valores) == -1) return false;

        return true;
    }

    public boolean modificacionInmueble(Inmueble inmueble) {
        ContentValues valores = new ContentValues();
        valores.put(DIRECCION, inmueble.getDireccion());
        valores.put(AMBIENTES, inmueble.getAmbientes());
        valores.put(TIPO, inmueble.getTipo());
        valores.put(USO, inmueble.getUso());
        valores.put(PRECIO, inmueble.getPrecio());
        valores.put(DISPONIBLE, inmueble.getDisponible());
        valores.put(ID_PROPIETARIO, inmueble.getIdPropietario());

        String [] args = new String[]{inmueble.getIdInmueble()+""};

        if(db.update(NOMBRE_TABLA, valores,"idInmueble=?", args) == 1) return true;

        return false;
    }

    public List<Inmueble> getInmuebles(int idPropietario){

        List<Inmueble> listaInmuebles = new ArrayList<>();
        String consulta= "SELECT * FROM inmueble WHERE idPropietario = " + idPropietario;
        Cursor resultSet = db.rawQuery(consulta, null);

            while (resultSet.moveToNext()){
                listaInmuebles.add(new Inmueble(
                                resultSet.getInt(0),
                                resultSet.getString(1),
                                resultSet.getInt(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getDouble(5),
                                resultSet.getInt(6),
                                resultSet.getInt(7)));
            }

        resultSet.close();
        return listaInmuebles;
    }

}
