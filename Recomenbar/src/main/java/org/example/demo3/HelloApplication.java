package org.example.demo3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HelloApplication extends Application {
    public static Scanner scanner;

    public static void main(String[] args) {
        // Lanza la aplicación JavaFX
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Instancia el gestor de pantallas y muestra la pantalla inicial
        GestorDePantallas gestorDePantallas = new GestorDePantallas(primaryStage);
        gestorDePantallas.mostrarPantalla("Home");
    }

    public static Connection conectarBD(String bd) throws SQLException {
        // Configuración de la conexión a la base de datos
        String host = "jdbc:mysql://localhost/";
        String user = "root";
        String pass = "Tinto56Ñ*52#";
        System.out.println("Conectando a la base de datos...");
        try {
            // Intenta establecer la conexión
            Connection conexion = DriverManager.getConnection(host + bd, user, pass);
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos: " + bd);
                System.out.println("-------------------");
            }
            return conexion;
        } catch (SQLException e) {
            // Captura y maneja los errores de conexión
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            throw new SQLException(e);
        }
    }
}