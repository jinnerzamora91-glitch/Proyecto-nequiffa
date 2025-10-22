/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nequiffa.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.nequiffa.model.*;

import java.io.FileOutputStream;

public class FacturaService {
    public void generarFactura(CuentaBancaria cuenta, String operacion, double monto, double comision) {
        // 🔹 COMPOSICIÓN: Factura existe solo dentro del servicio
        Factura factura = new Factura(cuenta, operacion, monto, comision);

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("factura.pdf"));
            document.open();

            document.add(new Paragraph("=== FACTURA NEQUI ==="));
            document.add(new Paragraph(factura.toString()));
            document.add(new Paragraph("Gracias por usar Nequi."));

            document.close();
            System.out.println("✅ Factura generada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error generando factura.");
        }
    }
}