package org.example.demo3.Entidades;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Entrada {
    private int id;
    private int idRerserva;
    private int idEvento;
    private String qr;
    private float precio;
    private boolean usado;

    public Entrada() {
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdRerserva() {
        return idRerserva;
    }

    public void setIdRerserva(int idRerserva) {
        this.idRerserva = idRerserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Método para generar un QR con la información de la reserva
    public void generarQR(int numeroPersonas) {
        String data = "Reserva ID: " + idRerserva + "\n" +
                "Evento ID: " + idEvento + "\n" +
                "Número de Personas: " + numeroPersonas + "\n" +
                "Precio: " + precio;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        String filePath = "D:\\Centa\\Documents\\Universidad\\Ing_Soft\\Recomenbar\\Recomenbar\\src\\main\\java\\org\\example\\demo3\\Entidades"
                + id + ".png";
        String charset = "UTF-8";

        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, charset);

        try {

            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
