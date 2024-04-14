package org.example.demo3.Entidades;

import java.util.List;

public class Reserva {
    private String fecha;
    private int cantEntradas;
    private boolean estadoReserva; //verdadero si esta activa falso si ya se uso o se cancelo
    private String metodoDePago;
    private String precioTotal;
    private List<Entrada> entradas;
    private Discoteca lugar;

    public Reserva (String fecha,Discoteca lugar,int cantEntradas,boolean estadoReserva,String metodoDePago,String precioTotal ){
        this.fecha=fecha;
        this.lugar=lugar;
        this.cantEntradas=cantEntradas;
        this.estadoReserva=estadoReserva;
        this.metodoDePago=metodoDePago;
        this.precioTotal=precioTotal;
    }

    public Reserva(Discoteca barSeleccionado, int cantidadPersonas, String fechaReserva) {
        this.cantEntradas=cantidadPersonas;
        this.estadoReserva=true;
        this.fecha=fechaReserva;
        this.lugar=barSeleccionado;

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
}