package org.example.demo3.Negocio;

public class Sesion {
    private static Sesion instancia;
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

    // Métodos para establecer y obtener el correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }
}
