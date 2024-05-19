package org.example.demo3.Entidades;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Entrada {
    private int id;
    private int id_discoteca;
    private boolean vip;
    private float precio;
    private byte[] qr;

    public Entrada(int id, int id_discoteca, boolean vip, float precio, byte[] qr) {
        this.id = id;
        this.id_discoteca = id_discoteca;
        this.vip = vip;
        this.precio = precio;
        this.qr = qr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_discoteca() {
        return id_discoteca;
    }

    public void setId_discoteca(int id_discoteca) {
        this.id_discoteca = id_discoteca;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public byte[] getQr() {
        return qr;
    }

    // Método para generar un QR con la información de la entrada
    public byte[] generarQR(int numeroPersonas) {
        String data = "Entrada ID: " + id + "\n" +
                "Discoteca ID: " + id_discoteca + "\n" +
                "VIP: " + vip + "\n" +
                "Precio: " + precio + "\n" +
                "Número de Personas: " + numeroPersonas;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        String charset = "UTF-8";

        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, charset);

        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setQr(byte[] qrCode) {
        this.qr = qrCode;
    }
}
