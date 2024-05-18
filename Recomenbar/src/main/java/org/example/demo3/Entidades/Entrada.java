package org.example.demo3.Entidades;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Entrada {
    private int id;
    private int idRerserva;
    private int idEvento;
    private String qr;
    private float precio;
    private boolean usado;
    private boolean VIP;

    public Entrada(){

    }
    //PRUEBA PUSH
    public boolean isUsado() {
        return usado;
    }
    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    public float getPrecio() {return precio; }
    public void setPrecio(float precio) {this.precio = precio;}
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
    public boolean getVip(){return VIP;}
    public void setVip(boolean VIP){this.VIP = VIP;}
}
