package org.example.demo3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestorDePantallas {
    private final Map<String, String> rutasFXML;
    private final Stage escenarioPrincipal;

    public GestorDePantallas(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.rutasFXML = new HashMap<>();
        this.rutasFXML.put("Home", "/org/example/demo3/Home.fxml");
        this.rutasFXML.put("Login", "/org/example/demo3/Login.fxml");
        this.rutasFXML.put("PostLogin", "/org/example/demo3/PostLogin.fxml");
        this.rutasFXML.put("Register", "/org/example/demo3/Registrer.fxml");
        this.rutasFXML.put("Reservar", "/org/example/demo3/Reservar.fxml");
    }

    public void mostrarPantalla(String rutaFXML) {
        try {
            String rutaCompletaFXML = rutasFXML.get(rutaFXML);
            if (rutaCompletaFXML == null) {
                System.err.println("No se encontró la ruta FXML para: " + rutaFXML);
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaCompletaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();  // Muestra la nueva pantalla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

