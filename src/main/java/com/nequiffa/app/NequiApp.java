/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.app;

import com.nequiffa.model.*;
import com.nequiffa.service.TransferenciaService;
import com.nequiffa.util.FacturaService;

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
            System.out.println("7. Generar factura PDF"); // 🔹 Nueva opción
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: crearCuentaExterna(); break;
                case 2: crearCuentaNequi(); break;
                case 3: depositar(); break;
                case 4: retirar(); break;
                case 5: transferir(); break;
                case 6: verSaldo(); break;
                case 7: generarFactura(); break; // 🔹 Nueva opción
                case 0: System.exit(0);
                default: System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void crearCuentaExterna() {
        System.out.print("Ingrese ID de la cuenta externa: ");
        String id = sc.nextLine();
        System.out.print("Ingrese nombre del usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String pass = sc.nextLine();
        System.out.print("Ingrese saldo inicial: ");
        double saldo = sc.nextDouble(); sc.nextLine();

        System.out.println("Seleccione banco: 1. Banco Bogotá | 2. Banco Popular");
        int banco = sc.nextInt(); sc.nextLine();

        if (banco == 1) {
            cuentaExterna = new BancoBogota(id, nombre, pass, saldo);
        } else {
            cuentaExterna = new BancoPopular(id, nombre, pass, saldo);
        }
        System.out.println("✅ Cuenta externa creada con éxito.");
    }

    private static void crearCuentaNequi() {
        if (cuentaExterna == null) {
            System.out.println("❌ Debe crear primero una cuenta externa.");
            return;
        }

        System.out.print("Ingrese ID de la cuenta Nequi: ");
        String id = sc.nextLine();
        System.out.print("Ingrese nombre del usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String pass = sc.nextLine();
        System.out.print("Ingrese saldo inicial: ");
        double saldo = sc.nextDouble(); sc.nextLine();

        // Cobro de comisión al crear Nequi
        double comision = 5000;
        if (cuentaExterna.getSaldo() >= comision) {
            cuentaExterna.retirar(comision);
            cuentaNequi = new CuentaNequi(id, nombre, pass, saldo, cuentaExterna);
            cuentaNequi.setTotalComisiones(cuentaNequi.getTotalComisiones() + comision);
            System.out.println("✅ Cuenta Nequi creada con éxito. Comisión cobrada: $" + comision);
        } else {
            System.out.println("❌ La cuenta externa no tiene fondos para pagar la comisión.");
        }
    }

    private static void depositar() {
        if (cuentaNequi == null) {
            System.out.println("❌ Debe crear primero una cuenta Nequi.");
            return;
        }
        System.out.print("Ingrese monto a depositar: ");
        double monto = sc.nextDouble(); sc.nextLine();
        cuentaNequi.depositar(monto);
        System.out.println("✅ Depósito exitoso.");
    }

    private static void retirar() {
        if (cuentaNequi == null) {
            System.out.println("❌ Debe crear primero una cuenta Nequi.");
            return;
        }
        System.out.print("Ingrese monto a retirar: ");
        double monto = sc.nextDouble(); sc.nextLine();
        double comision = cuentaNequi.retirar(monto);

        // Generar factura del retiro
        FacturaService facturaService = new FacturaService();
        facturaService.generarFactura(cuentaNequi, "Retiro", monto, comision);
    }

    private static void transferir() {
        if (cuentaNequi == null || cuentaExterna == null) {
            System.out.println("❌ Debe tener cuentas creadas.");
            return;
        }
        System.out.print("Ingrese monto a transferir de Nequi a cuenta externa: ");
        double monto = sc.nextDouble(); sc.nextLine();
        TransferenciaService ts = new TransferenciaService();
        ts.transferir(cuentaNequi, cuentaExterna, monto);
    }

    private static void verSaldo() {
        if (cuentaNequi != null) {
            System.out.println("Saldo Nequi: $" + cuentaNequi.getSaldo());
            System.out.println("Comisiones cobradas: $" + cuentaNequi.getTotalComisiones());
        }
        if (cuentaExterna != null) {
            System.out.println("Saldo Cuenta Externa: $" + cuentaExterna.getSaldo());
        }
    }

    private static void generarFactura() {
        if (cuentaNequi != null) {
            FacturaService facturaService = new FacturaService();
            facturaService.generarFactura(cuentaNequi, "Consulta / Facturación", 0, 0);
        } else {
            System.out.println("❌ Primero debe crear una cuenta Nequi.");
        }
    }
}