package com.example.tpintegradorfinal.model;

import java.io.Serializable;

public class Alquiler implements Serializable {

    private int idAlquiler;
    private double precio;
    private String fecha_inicio;
    private String fecha_fin;
    private int idInquilino;
    private Inquilino inquilino;
    private int idInmueble;
    private Inmueble inmueble;
    private int idPropietario;
    private Propietario propietario;

    public Alquiler() {

    }

    //CONTRUCTOR NORMAL
    public Alquiler(int idAlquiler, double precio, String fecha_inicio, String fecha_fin, int idInquilino, int idInmueble) {
        this.idAlquiler = idAlquiler;
        this.precio = precio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.idInquilino = idInquilino;
        this.idInmueble = idInmueble;
    }

    //CONTRUCTOR COMPLETO
    public Alquiler(int idAlquiler, double precio, String fecha_inicio, String fecha_fin, int idInquilino, Inquilino inquilino, int idInmueble, Inmueble inmueble, int idPropietario, Propietario propietario) {
        this.idAlquiler = idAlquiler;
        this.precio = precio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.idInquilino = idInquilino;
        this.inquilino = inquilino;
        this.idInmueble = idInmueble;
        this.inmueble = inmueble;
        this.idPropietario = idPropietario;
        this.propietario = propietario;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    //PARA MOSTRAR DATOS EN SPINNER INQUILINO
    @Override
    public String toString() {
        return "Hasta "+fecha_fin+"\nPrecio: "+precio;
    }
}
