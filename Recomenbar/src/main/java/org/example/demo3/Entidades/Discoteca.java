package org.example.demo3.Entidades;
import java.sql.Date;

public class Discoteca {
    private int id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private Date horarioApertura;
    private Date horarioCierre;
    private String tipoMusica;
    private int capacidadAforo;

    public Discoteca(String nombre, String descripcion, String ubicacion, Date horarioApertura, Date horarioCierre, String tipoMusica, int capacidadAforo){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.direccion=ubicacion;
        this.horarioApertura=horarioApertura;
        this.horarioCierre=horarioCierre;
        this.tipoMusica=tipoMusica;
        this.capacidadAforo=capacidadAforo;
    }

    public Discoteca() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return direccion;
    }

    public void setUbicacion(String ubicacion) {
        this.direccion = ubicacion;
    }

    public Date getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(Date horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public Date getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(Date horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public String getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(String tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    public int getCapacidadAforo() {
        return capacidadAforo;
    }

    public void setCapacidadAforo(int capacidadAforo) {
        this.capacidadAforo = capacidadAforo;
    }

    public void setPrecioEntrada(int precioEntrada) {
    }

    public void setTipo(int tipo) {
    }
}