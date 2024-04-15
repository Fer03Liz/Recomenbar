package org.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

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

    public static void main(String[] args) {
        launch(args);
    }
}
