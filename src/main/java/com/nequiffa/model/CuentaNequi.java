/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.model;

public class CuentaNequi extends CuentaBancaria {
    private double totalComisiones;
    private CuentaBancaria cuentaExterna; // 🔹 Agregación (Nequi tiene una cuenta bancaria externa)

    public CuentaNequi(String idCuenta, String nombreUsuario, String contraseña, double saldoInicial, CuentaBancaria cuentaExterna) {
        super(idCuenta, nombreUsuario, contraseña, saldoInicial);
        this.cuentaExterna = cuentaExterna;
        this.totalComisiones = 0;
    }

    @Override
    public double retirar(double monto) {
        double comision = monto * 0.01;
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

    public CuentaBancaria getCuentaExterna() { return cuentaExterna; }
    public double getTotalComisiones() { return totalComisiones; }
    public void setTotalComisiones(double totalComisiones) { this.totalComisiones = totalComisiones; }
}