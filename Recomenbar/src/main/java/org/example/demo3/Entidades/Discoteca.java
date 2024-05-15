package org.example.demo3.Entidades;


public class Discoteca {
    private int id;
    private String nombre;
    private String direccion;
    private String genero_musical;
    private int tipo;
    private float precio;

    public Discoteca(String nombre, String ubicacion, String genero_musical, int tipo, float precio){
        this.nombre=nombre;
        this.direccion=ubicacion;
        this.genero_musical=genero_musical;
        this.tipo=tipo;
        this.precio=precio;
    }
    public Discoteca() {
        this.id=0;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUbicacion() {
        return direccion;
    }
    public void setUbicacion(String ubicacion) {
        this.direccion = ubicacion;
    }
    public String getTipoMusica() {
        return genero_musical;
    }
    public void setTipoMusica(String tipoMusica) {
        this.genero_musical = tipoMusica;
    }
    public int getTipo(){return tipo;}
    public void setTipo(int tipo){this.tipo=tipo;}
    public float getPrecio(){return precio;}
    public void setPrecio(float precio){this.precio=precio;}

}