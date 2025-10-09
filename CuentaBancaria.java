/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.app;

public abstract class CuentaBancaria {
    protected String idCuenta;
    protected String nombreUsuario;
    protected String contraseña;
    protected double saldo;

    public CuentaBancaria(String idCuenta, String nombreUsuario, String contraseña, double saldoInicial) {
        this.idCuenta = idCuenta;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.saldo = saldoInicial;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public abstract double retirar(double monto);
}