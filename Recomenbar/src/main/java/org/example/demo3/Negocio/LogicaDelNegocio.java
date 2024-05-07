package org.example.demo3.Negocio;


import org.example.demo3.Entidades.Discoteca;
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

    public boolean registrarUsuario(String name, String email, int Edd, String password) throws SQLException {
        boolean insertado = false;
        Connection conexion = ConexionBD.getConexion(); // Obtén la conexión a través del Singleton

        String sql = "INSERT INTO registrarusuario VALUES(?,?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, name);
        sentencia.setString(2, email);
        sentencia.setInt(3, Edd);
        sentencia.setString(4, password);

        int filasINS = sentencia.executeUpdate();
        if (filasINS > 0) {
            insertado = true;
        }
        return insertado;
    }

    public boolean loginRealizado(String email, String password) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultados = null;
        boolean ingresado = false;
        try {
            conexion = ConexionBD.getConexion(); // Obtén la conexión a través del Singleton
            String sql = "SELECT * FROM registrarusuario WHERE Correo = ? AND Contraseña = ?";
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, email);
            sentencia.setString(2, password);

            resultados = sentencia.executeQuery();

            if (resultados.next()) {
                ingresado = true;
                System.out.println("Login exitoso.");
            } else {
                System.out.println("Correo o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return ingresado;
    }

    public boolean registrarReserva(int cantPersonas, Timestamp timestamp, String nombreBar, String correo) throws SQLException {

        boolean reservaregistrada = false;
        Connection conexion = ConexionBD.getConexion(); // Obtén la conexión a través del Singleton
        String sql = "INSERT INTO registroreservas VALUES(?,?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(1, cantPersonas);
        sentencia.setTimestamp(2,timestamp);
        sentencia.setString(3, nombreBar);
        sentencia.setString(4, correo);

        System.out.println("Mensaje de registro aquí");

        int filasINS = sentencia.executeUpdate();
        if (filasINS > 0) {
            // System.out.println("Mensaje de registro aquí");

            reservaregistrada = true;
            System.out.println("Reserva exitosa!!");
        } else {
            System.out.println("Algo salió mal...");
        }
        // System.out.println("Mensaje de registro aquí");

        return reservaregistrada;
    }

    public List<Discoteca> disponibles() throws SQLException {
        List<Discoteca> discotecas = new ArrayList<>();

        // Obtén la conexión a través del Singleton
        Connection conexion = ConexionBD.getConexion();

        // Preparar la sentencia SQL
        String sql = "SELECT nombre, direccion, tipoMusica FROM discoteca";
        PreparedStatement statement = conexion.prepareStatement(sql);

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Recorrer los resultados y agregar los nombres a la lista
        while (resultSet.next()) {
            Discoteca discoteca = new Discoteca();
            discoteca.setNombre( resultSet.getString("Nombre"));
            discoteca.setUbicacion(resultSet.getString("Direccion"));
            discoteca.setTipoMusica(resultSet.getString("TipoMusica"));
            discotecas.add(discoteca);
        }

        // Cerrar la conexión y liberar los recursos
        resultSet.close();
        statement.close();
        conexion.close();

        return discotecas;
    }
}