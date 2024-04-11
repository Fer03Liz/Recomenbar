package org.example.demo3.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String nombre;
    String correo;
    int edad;
    String contraseña;
    List<Encuesta> gustos = new ArrayList<>();
    List<Reserva> historial = new ArrayList<>();

    public Usuario(String nombre, String correo, int edad, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.contraseña=contraseña;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
