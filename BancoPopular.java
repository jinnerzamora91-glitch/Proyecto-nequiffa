/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.app;

public class BancoPopular extends CuentaBancaria {
    public BancoPopular(String idCuenta, String nombreUsuario, String contraseña, double saldoInicial) {
        super(idCuenta, nombreUsuario, contraseña, saldoInicial);
    }

    @Override
    public double retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("✅ Retiro exitoso en Banco Popular.");
            return 0;
        } else {
            System.out.println("❌ Fondos insuficientes en Banco Popular.");
            return 0;
        }
    }
}