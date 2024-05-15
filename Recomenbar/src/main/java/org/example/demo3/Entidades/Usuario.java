package org.example.demo3.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    int id;
    String nombre;
    int edad;
    String correo;
    String contraseña;
    int tipo;


    public Usuario(String nombre, String correo, int edad, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.contraseña=contraseña;
    }
    public Usuario() {

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
    public int getTipo() { return tipo;}
    public void setTipo(int tipo) {this.tipo = tipo;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

}
