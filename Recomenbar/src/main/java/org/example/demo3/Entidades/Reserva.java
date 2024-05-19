package org.example.demo3.Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.example.demo3.ConexionBD;
import org.example.demo3.Negocio.LogicaDelNegocio;

public class Reserva {
    private String fecha;
    private int cantEntradas;
    private boolean estadoReserva; // verdadero si esta activa falso si ya se uso o se cancelo
    private String metodoDePago;
    private String precioTotal;
    private List<Entrada> entradas;
    private Discoteca lugar;

    public Reserva(String fecha, Discoteca lugar, int cantEntradas, boolean estadoReserva, String metodoDePago,
            String precioTotal) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.cantEntradas = cantEntradas;
        this.estadoReserva = estadoReserva;
        this.metodoDePago = metodoDePago;
        this.precioTotal = precioTotal;
    }

    public Reserva(Discoteca barSeleccionado, int cantidadPersonas, String fechaReserva) {
        this.cantEntradas = cantidadPersonas;
        this.estadoReserva = true;
        this.fecha = fechaReserva;
        this.lugar = barSeleccionado;

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantEntradas() {
        return cantEntradas;
    }

    public void setCantEntradas(int cantEntradas) {
        this.cantEntradas = cantEntradas;
    }

    public boolean getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(boolean estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public boolean registrarReserva(int idUsuario, int idDiscoteca, int idEntrada, int idEvento, Timestamp timestamp,
            int cantidadBoletas, boolean valida) throws SQLException {
        boolean reservaregistrada = false;
        Connection conexion = ConexionBD.getConexion();
        String sqlReserva = "INSERT INTO reserva (id_usuario, id_discoteca, id_entrada, id_evento, fecha, cantidad_boletas, valida) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement sentenciaReserva = conexion.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS);

        sentenciaReserva.setInt(1, idUsuario);
        sentenciaReserva.setInt(2, idDiscoteca);
        sentenciaReserva.setInt(3, idEntrada);
        sentenciaReserva.setTimestamp(4, timestamp);
        sentenciaReserva.setInt(5, cantidadBoletas);
        sentenciaReserva.setBoolean(6, valida);

        int filasINS = sentenciaReserva.executeUpdate();
        if (filasINS > 0) {
            reservaregistrada = true;
            ResultSet generatedKeys = sentenciaReserva.getGeneratedKeys();
            if (generatedKeys.next()) {
                LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
                Entrada entrada = logica.obtenerEntradaPorReserva(idUsuario, idDiscoteca, timestamp);
                if (entrada != null) {
                    byte[] qrCode = entrada.generarQR(cantidadBoletas);
                    if (qrCode != null) {
                        String sqlQR = "UPDATE entrada SET qr_code = ? WHERE id = ?";
                        PreparedStatement sentenciaQR = conexion.prepareStatement(sqlQR);
                        sentenciaQR.setBytes(1, qrCode);
                        sentenciaQR.setInt(2, entrada.getId());
                        sentenciaQR.executeUpdate();
                    }
                }
            }
        } else {
            System.out.println("Algo salió mal...");
        }
        return reservaregistrada;
    }

}