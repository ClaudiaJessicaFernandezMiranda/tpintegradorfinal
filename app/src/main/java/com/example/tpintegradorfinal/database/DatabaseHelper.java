package com.example.tpintegradorfinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Esta ejecucion se hace 1 sola vez, aca solo entra una vez, crea y no vuelve
    //Hasta borrar la base de datos de nuevo
    //Para recrear la base de datos context.deleteDatabase(DB_NOMBRE);
    private static final String DB_NOMBRE = "inmobiliariaAPP.db";
    public DatabaseHelper(Context context){
        super(context, DB_NOMBRE,null,1);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabasePropietario.CREAR_TABLA);
        db.execSQL(DatabaseInmueble.CREAR_TABLA);
        db.execSQL(DatabaseInquilino.CREAR_TABLA);
        db.execSQL(DatabasePago.CREAR_TABLA);
        db.execSQL(DatabaseAlquiler.CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabasePropietario.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInmueble.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInquilino.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabasePago.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseAlquiler.NOMBRE_TABLA);
        onCreate(db);
    }

}
