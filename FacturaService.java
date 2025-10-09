/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

public class FacturaService {

    public void generarFactura(CuentaBancaria cuenta, String operacion, double monto, double comision) {
        Document document = new Document();
        String ruta = "factura.pdf"; // Se guarda en la carpeta del proyecto
        try {
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            document.add(new Paragraph("================================="));
            document.add(new Paragraph("          FACTURA NEQUI"));
            document.add(new Paragraph("=================================\n"));

            document.add(new Paragraph("Cuenta: " + cuenta.getIdCuenta()));
            document.add(new Paragraph("Usuario: " + cuenta.getNombreUsuario()));
            document.add(new Paragraph("Saldo Actual: $" + cuenta.getSaldo() + "\n"));

            if (cuenta instanceof CuentaNequi) {
                CuentaNequi nequi = (CuentaNequi) cuenta;
                if (nequi.getCuentaExterna() != null) {
                    document.add(new Paragraph("Cuenta Externa: " + nequi.getCuentaExterna().getIdCuenta()));
                    document.add(new Paragraph("Usuario Externo: " + nequi.getCuentaExterna().getNombreUsuario()));
                    document.add(new Paragraph("Saldo Externo: $" + nequi.getCuentaExterna().getSaldo() + "\n"));
                }
                document.add(new Paragraph("Total Comisiones: $" + nequi.getTotalComisiones() + "\n"));
            }

            document.add(new Paragraph("---------------------------------"));
            document.add(new Paragraph("Operación: " + operacion));
            document.add(new Paragraph("Monto: $" + monto));
            document.add(new Paragraph("Comisión aplicada: $" + comision));
            document.add(new Paragraph("Fecha: " + LocalDateTime.now()));
            document.add(new Paragraph("================================="));
            document.add(new Paragraph("     ¡Gracias por usar Nequi!"));
            document.add(new Paragraph("================================="));

            document.close();
            System.out.println("✅ Factura generada en: " + ruta);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}