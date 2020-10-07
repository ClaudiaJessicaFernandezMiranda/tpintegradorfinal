package com.example.tpintegradorfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpintegradorfinal.model.Propietario;

import java.util.ArrayList;
import java.util.List;

public class DatabasePropietario{

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DatabasePropietario(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    //Usuario Logeado
    public static final Propietario logeado = new Propietario();
    //COLUMNAS
    public static final String NOMBRE_TABLA = "propietario";
    private static final String ID = "idPropietario";
    private static final String DNI = "dni";
    private static final String APELLIDO = "apellido";
    private static final String NOMBRE = "nombre";
    private static final String TELEFONO = "telefono";
    private static final String MAIL = "mail";
    private static final String CLAVE = "password";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DNI + " TEXT NULL, "
            + APELLIDO + " TEXT NULL, "
            + NOMBRE + " TEXT NULL, "
            + TELEFONO + " TEXT NULL, "
            + MAIL + " TEXT NULL, "
            + CLAVE + " TEXT NULL "
            + ");";

    public boolean altaPropietario(Propietario propietario) {
        ContentValues valores = new ContentValues();
        valores.put(ID, (Integer) null);
        valores.put(DNI, propietario.getDni());
        valores.put(APELLIDO, propietario.getApellido());
        valores.put(NOMBRE, propietario.getNombre());
        valores.put(TELEFONO, propietario.getTelefono());
        valores.put(MAIL, propietario.getEmail());
        valores.put(CLAVE, propietario.getClave());

        long resultado = db.insert(NOMBRE_TABLA,null, valores);

        return resultado != -1; //Si resutado es = -1, no logro insertar, retorna false.
                                //Si resutado es != -1 logro insertar, retorna true.
    }

    public boolean modificacionPropietario(Propietario propietario) {
        ContentValues valores = new ContentValues();
        valores.put(DNI, propietario.getDni());
        valores.put(APELLIDO, propietario.getApellido());
        valores.put(NOMBRE, propietario.getNombre());
        valores.put(TELEFONO, propietario.getTelefono());
        valores.put(MAIL, propietario.getEmail());
        valores.put(CLAVE, propietario.getClave());
        String [] args = new String[]{propietario.getIdPropietario()+""};

        if(db.update(NOMBRE_TABLA, valores,"idPropietario=?", args) == 1){
            logeado.setDni(propietario.getDni());
            logeado.setApellido(propietario.getApellido());
            logeado.setNombre(propietario.getNombre());
            logeado.setTelefono(propietario.getTelefono());
            logeado.setEmail(propietario.getEmail());
            logeado.setClave(propietario.getClave());

            return true;
        }

        return  false;
    }

    public List<Propietario> getPropitarios(){
        List<Propietario> listaPropietarios = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM propietario", null);
        while (cursor.moveToNext()){
            listaPropietarios.add(new Propietario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
                    )
            );
        }
        cursor.close();
        return listaPropietarios;
    }

    public boolean comprobarRegistro(String correo) {
        boolean esta;
        Cursor resultSet = db.rawQuery("SELECT mail FROM propietario" + " WHERE mail='"+correo+"'", null);

        if(resultSet.getCount()<=0){
            esta = false;
        }else{
            esta = true;
        }
        return esta;
    }

    public boolean login(String email, String clave){
        Cursor resultSet = db.rawQuery("SELECT * FROM propietario" + " WHERE mail='"+email+"' AND password='"+clave+"'",null);
        if(resultSet.moveToFirst()){
            logeado.setIdPropietario(resultSet.getInt(0));
            logeado.setDni(resultSet.getString(1));
            logeado.setApellido(resultSet.getString(2));
            logeado.setNombre(resultSet.getString(3));
            logeado.setTelefono(resultSet.getString(4));
            logeado.setEmail(resultSet.getString(5));
            logeado.setClave(resultSet.getString(6));
            return true;
        }
        else return false;
    }

}
