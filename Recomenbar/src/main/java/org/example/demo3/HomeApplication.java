package org.example.demo3;

import javafx.application.Application;
import javafx.stage.Stage;

import org.example.demo3.Entidades.Entrada;
import org.example.demo3.Negocio.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class HomeApplication extends Application {
    private static Connection connection; // Declara la conexión como estática

    @Override
    public void start(Stage primaryStage) {
        try {
            // Conecta a la base de datos si no se ha conectado previamente
            if (connection == null || connection.isClosed()) {
                connection = ConexionBD.getConexion(); // Obtiene la conexión usando el Singleton
            }
            // Instancia el gestor de pantallas y muestra la pantalla inicial
            GestorDePantallas gestorDePantallas = new GestorDePantallas();
            gestorDePantallas.mostrarPantallaHome(primaryStage);
        } catch (SQLException e) {
            // Maneja la excepción SQLException
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Inicia la aplicación JavaFX
        launch(args);

    }
}