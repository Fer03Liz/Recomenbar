package org.example.demo3.Entidades;

import java.sql.Timestamp;
import java.util.Date;

public class Reserva {
    private int id;
    private int idUsuario;
    private int idDiscoteca;
    private int idEntrada;
    private int idEvento;
    private Date fecha;
    private int cantEntradas;
    private boolean valida; //verdadero si esta activa falso si ya se uso o se cancelo

    public Reserva() {
    }

    public Reserva(int id, int idUsuario, int idDiscoteca, int idEntrada, int idEvento, Timestamp fecha, int cantEntradas, boolean valida) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idDiscoteca = idDiscoteca;
        this.idEntrada = idEntrada;
        this.idEvento = idEvento;
        this.fecha = new Date(fecha.getTime()); // Convertir Timestamp a Date
        this.cantEntradas = cantEntradas;
        this.valida = valida;
    }


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getIdUsuario() {return idUsuario;}
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public int getIdDiscoteca() {return idDiscoteca;}
    public void setIdDiscoteca(int idDiscoteca) {this.idDiscoteca = idDiscoteca;}
    public int getIdEntrada() {return idEntrada;}
    public void setIdEntrada(int id) {this.idEntrada = id;}
    public int getIdEvento() {return idEvento;}
    public void setIdEvento(int id) {this.idEvento = id;}
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getCantEntradas() {
        return cantEntradas;
    }
    public void setCantEntradas(int cantEntradas) {
        this.cantEntradas = cantEntradas;
    }
    public boolean getEstadoReserva() {
        return valida;
    }
    public void setEstadoReserva(boolean estadoReserva) {
        this.valida = estadoReserva;
    }

}