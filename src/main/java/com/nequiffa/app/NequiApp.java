/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.app;

import com.nequiffa.model.*;
import com.nequiffa.service.*;

import java.util.Scanner;

public class NequiApp {
    private static Scanner sc = new Scanner(System.in);
    private static CuentaBancaria cuentaExterna;
    private static CuentaNequi cuentaNequi;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU NEQUI ===");
            System.out.println("1. Crear cuenta externa");
            System.out.println("2. Crear cuenta Nequi");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Transferir");
            System.out.println("6. Ver saldo");
            System.out.println("7. Generar factura PDF");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearCuentaExterna();
                case 2 -> crearCuentaNequi();
                case 3 -> depositar();
                case 4 -> retirar();
                case 5 -> transferir();
                case 6 -> verSaldo();
                case 7 -> generarFactura();
                case 0 -> System.exit(0);
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void crearCuentaExterna() {
        System.out.print("ID cuenta externa: ");
        String id = sc.nextLine();
        System.out.print("Nombre usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = sc.nextDouble(); sc.nextLine();

        System.out.println("Seleccione banco: 1. Bogotá | 2. Popular");
        int banco = sc.nextInt(); sc.nextLine();

        cuentaExterna = (banco == 1)
                ? new BancoBogota(id, nombre, pass, saldo)
                : new BancoPopular(id, nombre, pass, saldo);

        System.out.println("✅ Cuenta externa creada.");
    }

    private static void crearCuentaNequi() {
        if (cuentaExterna == null) {
            System.out.println("❌ Cree primero una cuenta externa.");
            return;
        }

        System.out.print("ID cuenta Nequi: ");
        String id = sc.nextLine();
        System.out.print("Nombre usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = sc.nextDouble(); sc.nextLine();
        
        double comision = 5000;
        if (cuentaExterna.getSaldo() >= comision) {
            cuentaExterna.retirar(comision);
            cuentaNequi = new CuentaNequi(id, nombre, pass, saldo, cuentaExterna);
            cuentaNequi.setTotalComisiones(comision);
            System.out.println("✅ Cuenta Nequi creada. Comisión: $" + comision);
        } else {
            System.out.println("❌ Fondos insuficientes para crear Nequi.");
        }
    }

    private static void depositar() {
        if (cuentaNequi == null) {
            System.out.println("❌ Cree primero la cuenta Nequi.");
            return;
        }
        System.out.print("Monto a depositar: ");
        double monto = sc.nextDouble(); sc.nextLine();
        cuentaNequi.depositar(monto);
        System.out.println("✅ Depósito exitoso.");
    }

    private static void retirar() {
        if (cuentaNequi == null) {
            System.out.println("❌ Cree primero la cuenta Nequi.");
            return;
        }
        System.out.print("Monto a retirar: ");
        double monto = sc.nextDouble(); sc.nextLine();
        double comision = cuentaNequi.retirar(monto);

        new FacturaService().generarFactura(cuentaNequi, "Retiro", monto, comision);
    }

    private static void transferir() {
        if (cuentaNequi == null || cuentaExterna == null) {
            System.out.println("❌ Debe tener ambas cuentas.");
            return;
        }
        System.out.print("Monto a transferir: ");
        double monto = sc.nextDouble(); sc.nextLine();
        new TransferenciaService().transferir(cuentaNequi, cuentaExterna, monto);
    }

    private static void verSaldo() {
        if (cuentaNequi != null)
            System.out.println("Saldo Nequi: $" + cuentaNequi.getSaldo());
        if (cuentaExterna != null)
            System.out.println("Saldo Externa: $" + cuentaExterna.getSaldo());
    }

    private static void generarFactura() {
        if (cuentaNequi != null)
            new FacturaService().generarFactura(cuentaNequi, "Consulta", 0, 0);
        else
            System.out.println("❌ No existe cuenta Nequi.");
    }
}