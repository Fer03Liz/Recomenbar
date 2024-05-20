package org.example.demo3.Entidades;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Entrada {
    private int id;
    private int idR;
    private int idDiscoteca;
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
    public int getIdDiscoteca(){
        return idDiscoteca;
    }

    public void setIdDiscoteca(int idDiscoteca){
        this.idDiscoteca = idDiscoteca;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdR() {
        return idR;
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
