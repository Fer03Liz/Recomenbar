package org.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML y obtener el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        HelloController helloController = loader.getController();

        // Instanciar el gestor de pantallas y establecerlo en el controlador
        GestorDePantallas gestorDePantallas = new GestorDePantallas(primaryStage);
        helloController.setGestorDePantallas(gestorDePantallas);

        // Configurar la escena y mostrarla
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
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
