package org.example.demo3.Entidades;

import java.util.List;

public class Reserva {
    private String fecha;
    private String hora;
    private String cantEntradas;
    private String estadoReserva;
    private String metodoDePago;
    private String precioTotal;
    private String infoContacto;
    private List<Entrada> entradas;

    public Reserva (String fecha,String hora,String cantEntradas,String estadoReserva,String metodoDePago,String precioTotal,String infoContacto){
        this.fecha=fecha;
        this.hora=hora;
        this.cantEntradas=cantEntradas;
        this.estadoReserva=estadoReserva;
        this.metodoDePago=metodoDePago;
        this.precioTotal=precioTotal;
        this.infoContacto=infoContacto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCantEntradas() {
        return cantEntradas;
    }

    public void setCantEntradas(String cantEntradas) {
        this.cantEntradas = cantEntradas;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
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

    public String getInfoContacto() {
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }
}