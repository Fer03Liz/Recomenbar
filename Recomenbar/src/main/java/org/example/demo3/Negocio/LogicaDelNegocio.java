package org.example.demo3.Negocio;

import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.HelloApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogicaDelNegocio {
    private static LogicaDelNegocio instancia;

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private LogicaDelNegocio() {
    }

    // Método estático para obtener la instancia única de LogicaDelNegocio
    public static LogicaDelNegocio getInstancia() {
        if (instancia == null) {
            instancia = new LogicaDelNegocio();
        }
        return instancia;
    }

    public boolean registrarUsuario(String name, String email, int Edd, String password) {
        boolean insertado = false;
        try (Connection conexion = HelloApplication.conectarBD("recomenbar");
             PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO registrarusuario (Name, Correo, Edd, Contraseña) VALUES (?, ?, ?, ?)")) {

            sentencia.setString(1, name);
            sentencia.setString(2, email);
            sentencia.setInt(3, Edd);
            sentencia.setString(4, password);

            int filasINS = sentencia.executeUpdate();
            if (filasINS > 0) {
                insertado = true;
                System.out.println("Insertado con éxito!!");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        return insertado;
    }

    public boolean loginRealizado(String email, String password) {
        boolean ingresado = false;
        try (Connection conexion = HelloApplication.conectarBD("recomenbar");
             PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM registrarusuario WHERE Correo = ? AND Contraseña = ?")) {

            sentencia.setString(1, email);
            sentencia.setString(2, password);

            try (ResultSet resultados = sentencia.executeQuery()) {
                if (resultados.next()) {
                    ingresado = true;
                    System.out.println("Login exitoso.");
                } else {
                    System.out.println("Correo o contraseña incorrectos.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el login: " + e.getMessage());
        }
        return ingresado;
    }

    public boolean registrarReserva(int cantPersonas, Timestamp timestamp) {
        boolean reservaregistrada = false;
        try (Connection conexion = HelloApplication.conectarBD("world");
             PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO registroreservas (CantidadPersonas, FechaReserva) VALUES (?, ?)")) {

            System.out.println("Cantidad de personas: ");
            cantPersonas = HelloApplication.scanner.nextInt();
            sentencia.setInt(1, cantPersonas);
            HelloApplication.scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            System.out.println("Fecha de la reserva: ");
            timestamp = new Timestamp(System.currentTimeMillis());
            sentencia.setTimestamp(2, timestamp);

            int filasINS = sentencia.executeUpdate();
            if (filasINS > 0) {
                reservaregistrada = true;
                System.out.println("Reserva exitosa!!");
            } else {
                System.out.println("Algo salió mal...");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar la reserva: " + e.getMessage());
        }
        return reservaregistrada;
    }

    public List<Discoteca> disponibles() {
        List<Discoteca> disponibles = new ArrayList<>();
        // Agrega aquí la lógica para obtener las discotecas disponibles
        return disponibles;
    }

}
