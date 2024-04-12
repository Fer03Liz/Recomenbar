package org.example.demo3;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Instancia el gestor de pantallas y muestra la pantalla inicial
        GestorDePantallas gestorDePantallas = new GestorDePantallas(primaryStage);
        gestorDePantallas.mostrarPantalla("Home");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
