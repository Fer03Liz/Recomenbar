package org.example.demo3.Negocio;

import org.example.demo3.Entidades.Usuario;

public class Sesion {
    private static Sesion instancia;
    private String correo;
    private Usuario usuarioActual;

    // Constructor privado para evitar instanciación externa
    private Sesion() {
    }

    // Método estático para obtener la instancia única de Sesion
    public static Sesion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    // Métodos para establecer y obtener el correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

}
