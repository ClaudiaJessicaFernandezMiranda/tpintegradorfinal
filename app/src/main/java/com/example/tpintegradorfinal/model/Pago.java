package com.example.tpintegradorfinal.model;

import java.io.Serializable;

public class Pago implements Serializable {

    private int idPago;
    private int nro_pago;
    private String fecha;
    private double importe;
    private int idAlquiler;

    public Pago() {

    }

    public Pago(int idPago, int nro_pago, String fecha, double importe, int idAlquiler) {
        this.idPago = idPago;
        this.nro_pago = nro_pago;
        this.fecha = fecha;
        this.importe = importe;
        this.idAlquiler = idAlquiler;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getNro_pago() {
        return nro_pago;
    }

    public void setNro_pago(int nro_pago) {
        this.nro_pago = nro_pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

}
