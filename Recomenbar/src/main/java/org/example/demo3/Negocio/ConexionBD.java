package org.example.demo3.Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static Connection connection; // Declara la conexión como estática

    private ConexionBD() {} // Constructor privado para evitar la creación de instancias

    public static Connection getConexion() throws SQLException {
        // Configuración de la conexión a la base de datos
        String host = "jdbc:mysql://localhost/";
        String bd = "recomenbar";
        String user = "root";
        String pass = "12345678";

        // Verifica si ya existe una conexión
        if (connection == null || connection.isClosed()) {
            System.out.println("Conectando a la base de datos...");
            try {
                // Intenta establecer la conexión
                connection = DriverManager.getConnection(host + bd, user, pass);
                if (connection != null) {
                    System.out.println("Conexión exitosa a la base de datos: " + bd);
                    System.out.println("-------------------");
                }
            } catch (SQLException e) {
                // Captura y maneja los errores de conexión
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                throw new SQLException(e);
            }
        }
        return connection;
    }
}