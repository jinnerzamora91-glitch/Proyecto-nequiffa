/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.app;

import com.nequiffa.app.CuentaBancaria;

/**
 * CuentaNequi mantiene referencia a una CuentaBancaria externa
 * y lleva el acumulado de comisiones. El método retirar aplica 1% de comision.
 */
public class CuentaNequi extends CuentaBancaria {
    private double totalComisiones;
    private CuentaBancaria cuentaExterna;

    public CuentaNequi(String idCuenta, String nombreUsuario, String contraseña, double saldoInicial, CuentaBancaria cuentaExterna) {
        super(idCuenta, nombreUsuario, contraseña, saldoInicial);
        this.totalComisiones = 0;
        this.cuentaExterna = cuentaExterna;
    }

    @Override
    public double retirar(double monto) {
        double comision = monto * 0.01; // 1% de comisión
        double total = monto + comision;

        if (saldo >= total) {
            saldo -= total;
            totalComisiones += comision;
            System.out.println("✅ Retiro exitoso. Comisión: $" + comision);
            return comision;
        } else {
            System.out.println("❌ Fondos insuficientes.");
            return 0;
        }
    }

    public double getTotalComisiones() {
        return totalComisiones;
    }

    public void setTotalComisiones(double totalComisiones) {
        this.totalComisiones = totalComisiones;
    }

    public CuentaBancaria getCuentaExterna() {
        return cuentaExterna;
    }
}