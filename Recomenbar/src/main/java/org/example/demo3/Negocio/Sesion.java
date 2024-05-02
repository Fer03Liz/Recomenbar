package org.example.demo3.Negocio;

public class Sesion {
    private static Sesion instancia;
    private String nombre;
    private String correo;

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

    // Métodos para establecer y obtener el nombre y el correo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}
