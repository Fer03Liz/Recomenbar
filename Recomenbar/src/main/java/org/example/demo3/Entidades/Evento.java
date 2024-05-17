package org.example.demo3.Entidades;

import java.sql.Date;
import java.util.List;

public class Evento {
    private int id;
    private int id_discoteca;
    private String nombre;
    private Date fecha;
    private float precio;
    private boolean privado;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public float getPrecio() {return precio;    }
    public void setPrecio(float precio) {this.precio = precio;}
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getId_discoteca() {return id_discoteca;    }
    public void setId_discoteca(int id_discoteca) {this.id_discoteca = id_discoteca;    }
    public boolean isPrivado() {return privado;}
    public void setPrivado(boolean privado) {this.privado = privado;}
}

