/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.model;

import java.time.LocalDateTime;

public class Factura {
    private String idCuenta;
    private String nombreUsuario;
    private String operacion;
    private double monto;
    private double comision;
    private LocalDateTime fecha;

    public Factura(CuentaBancaria cuenta, String operacion, double monto, double comision) {
        this.idCuenta = cuenta.getIdCuenta();
        this.nombreUsuario = cuenta.getNombreUsuario();
        this.operacion = operacion;
        this.monto = monto;
        this.comision = comision;
        this.fecha = LocalDateTime.now();
    }

    public String toString() {
        return "Factura [" + fecha + "]\n" +
                "Cuenta: " + idCuenta + "\n" +
                "Usuario: " + nombreUsuario + "\n" +
                "Operación: " + operacion + "\n" +
                "Monto: $" + monto + "\n" +
                "Comisión: $" + comision + "\n";
    }
}