package com.example.tpintegradorfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpintegradorfinal.model.Alquiler;
import com.example.tpintegradorfinal.model.Inmueble;
import com.example.tpintegradorfinal.model.Inquilino;
import com.example.tpintegradorfinal.model.Propietario;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAlquiler {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public DatabaseAlquiler(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    //COLUMNAS
    public static final String NOMBRE_TABLA = "alquiler";
    private static final String ID = "idAlquiler";
    private static final String PRECIO = "precio";
    private static final String FECHA_INICIO = "fecha_inicio";
    private static final String FECHA_FIN = "fecha_fin";
    private static final String ID_INQUILINO = "idInquilino";
    private static final String ID_INMUEBLE = "idInmueble";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRECIO + " TEXT NULL, "
            + FECHA_INICIO + " TEXT NULL, "
            + FECHA_FIN + " TEXT NULL, "
            + ID_INQUILINO + " INTEGER NOT NULL, "
            + ID_INMUEBLE + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+ID_INQUILINO+") REFERENCES "+"inquilino"+"("+"idInquilino"+"), "
            + " FOREIGN KEY ("+ID_INMUEBLE+") REFERENCES "+"inmueble"+"("+"idInmueble"+")"
            + ");";

    public boolean altaAlquiler(Alquiler alquiler) {
        ContentValues valores = new ContentValues();
        valores.put(ID, (Integer) null);
        valores.put(PRECIO, alquiler.getPrecio());
        valores.put(FECHA_INICIO, alquiler.getFecha_inicio());
        valores.put(FECHA_FIN, alquiler.getFecha_fin());
        valores.put(ID_INQUILINO, alquiler.getIdInquilino());
        valores.put(ID_INMUEBLE, alquiler.getIdInmueble());

        if(db.insert(NOMBRE_TABLA, null, valores) != -1) return true;
        return false;
    }

    public boolean modificacionAlquiler(Alquiler alquiler) {
        ContentValues valores = new ContentValues();
        valores.put(PRECIO, alquiler.getPrecio());
        valores.put(FECHA_INICIO, alquiler.getFecha_inicio());
        valores.put(FECHA_FIN, alquiler.getFecha_fin());
        valores.put(ID_INQUILINO, alquiler.getIdInquilino());
        valores.put(ID_INMUEBLE, alquiler.getIdInmueble());

        String [] args = new String[]{alquiler.getIdAlquiler()+""};

        if(db.update(NOMBRE_TABLA, valores,"idAlquiler=?", args) == 1) return true;
        return false;
    }

    public List<Alquiler> getAlquileresPorIdInmueble(int idInmueble){

        List<Alquiler> listaAlquileres = new ArrayList<>();
        String consulta= "SELECT * FROM alquiler a INNER JOIN inmueble i ON a.idInmueble = i.idInmueble WHERE a.idInmueble = "+idInmueble;

        Cursor resultSet = db.rawQuery(consulta, null);

        while (resultSet.moveToNext()){
            Alquiler alquiler = new Alquiler();
            alquiler.setIdAlquiler(resultSet.getInt(0));
            alquiler.setPrecio(resultSet.getDouble(1));
            alquiler.setFecha_inicio(resultSet.getString(2));
            alquiler.setFecha_fin(resultSet.getString(3));
            alquiler.setIdInquilino(resultSet.getInt(4));
            alquiler.setIdInmueble(resultSet.getInt(5));
            listaAlquileres.add(alquiler);
        }

        resultSet.close();

        return listaAlquileres;
    }

    public Alquiler getAlquilerSeleccionado(int idInquilino, int idInmueble) {

        String consulta= "SELECT a.precio, a.fecha_inicio, a.fecha_fin, i.direccion, i.tipo, i.uso, i.precio, inq.apellido, inq.nombre, inq.direccion, inq.telefono, p.apellido, p.nombre " +
                "FROM alquiler a INNER JOIN inmueble i ON a.idInmueble = i.idInmueble " +
                "INNER JOIN inquilino inq ON a.idInquilino = inq.idInquilino " +
                "INNER JOIN propietario p ON i.idPropietario " +
                "WHERE a.idInquilino = "+idInquilino+" " +
                "AND a.idInmueble = "+idInmueble+" " +
                "AND i.idPropietario ="+DatabasePropietario.logeado.getIdPropietario();
        Cursor resultSet = db.rawQuery(consulta, null);

        Alquiler alquiler = new Alquiler();
        alquiler.setPropietario(new Propietario());
        alquiler.setInquilino(new Inquilino());
        alquiler.setInmueble(new Inmueble());
        while (resultSet.moveToNext()){
            alquiler.setPrecio(resultSet.getDouble(0));
            alquiler.setFecha_inicio(resultSet.getString(1));
            alquiler.setFecha_fin(resultSet.getString(2));
            alquiler.getInmueble().setDireccion(resultSet.getString(3));
            alquiler.getInmueble().setTipo(resultSet.getString(4));
            alquiler.getInmueble().setUso(resultSet.getString(5));
            alquiler.getInmueble().setPrecio(resultSet.getDouble(6));
            alquiler.getInquilino().setApellido(resultSet.getString(7));
            alquiler.getInquilino().setNombre(resultSet.getString(8));
            alquiler.getInquilino().setDireccion(resultSet.getString(9));
            alquiler.getInquilino().setTelefono(resultSet.getString(10));
            alquiler.getPropietario().setApellido(resultSet.getString(11));
            alquiler.getPropietario().setNombre(resultSet.getString(12));
        }

        resultSet.close();

        return alquiler;
    }


}
