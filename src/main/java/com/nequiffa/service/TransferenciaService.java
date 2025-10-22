/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.service;

import com.nequiffa.model.CuentaBancaria;

public class TransferenciaService {
    // Relación de asociación: usa cuentas, pero no las posee
    public boolean transferir(CuentaBancaria origen, CuentaBancaria destino, double monto) {
        if (origen.getSaldo() >= monto) {
            origen.retirar(monto);
            destino.depositar(monto);
            System.out.println("✅ Transferencia exitosa.");
            return true;
        } else {
            System.out.println("❌ Fondos insuficientes.");
            return false;
        }
    }
}