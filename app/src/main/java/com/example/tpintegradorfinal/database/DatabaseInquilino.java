package com.example.tpintegradorfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpintegradorfinal.model.Inquilino;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInquilino {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public DatabaseInquilino(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    //COLUMNAS
    public static final String NOMBRE_TABLA = "inquilino";
    private static final String ID = "idInquilino";
    private static final String DNI = "dni";
    private static final String APELLIDO = "apellido";
    private static final String NOMBRE = "nombre";
    private static final String DIRECCION = "direccion";
    private static final String TELEFONO = "telefono";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DNI + " TEXT NULL, "
            + APELLIDO + " TEXT NULL, "
            + NOMBRE + " TEXT NULL, "
            + DIRECCION + " TEXT NULL, "
            + TELEFONO + " TEXT NULL "
            + ");";

    public boolean altaInquilino(Inquilino inquilino) {
        ContentValues valores = new ContentValues();
        valores.put(ID, (Integer) null);
        valores.put(DNI, inquilino.getDni());
        valores.put(APELLIDO, inquilino.getApellido());
        valores.put(NOMBRE, inquilino.getNombre());
        valores.put(DIRECCION, inquilino.getDireccion());
        valores.put(TELEFONO, inquilino.getTelefono());

        if(db.insert(NOMBRE_TABLA, null, valores) != -1) return true;
        return false;
    }

    public boolean modificacionInquilino(Inquilino inquilino) {
        ContentValues valores = new ContentValues();
        valores.put(ID, inquilino.getIdInquilino());
        valores.put(DNI, inquilino.getDni());
        valores.put(APELLIDO, inquilino.getApellido());
        valores.put(NOMBRE, inquilino.getNombre());
        valores.put(DIRECCION, inquilino.getDireccion());
        valores.put(TELEFONO, inquilino.getTelefono());

        String [] args = new String[]{inquilino.getIdInquilino()+""};

        if(db.update(NOMBRE_TABLA, valores,"idInquilino=?", args) == 1) return true;
        return false;
    }

    public List<Inquilino> getInquilinos(){

        List<Inquilino> listaInquilinos = new ArrayList<>();
        String consulta = "SELECT * FROM inquilino";
        Cursor resultSet = db.rawQuery(consulta, null);

            while (resultSet.moveToNext()){
                listaInquilinos.add(new Inquilino(
                                resultSet.getInt(0),
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)
                ));
            }

        resultSet.close();

        return listaInquilinos;
    }

}
