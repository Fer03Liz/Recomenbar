package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestorDePantallas {
    private static GestorDePantallas instancia;
    private final Map<String, String> rutasFXML;

    GestorDePantallas() {
        this.rutasFXML = new HashMap<>();
        this.rutasFXML.put("Home", "/org/example/demo3/Home.fxml");
        this.rutasFXML.put("Login", "/org/example/demo3/Login.fxml");
        this.rutasFXML.put("PostLogin", "/org/example/demo3/PostLogin.fxml");
        this.rutasFXML.put("Register", "/org/example/demo3/Registrer.fxml");
        this.rutasFXML.put("Res" +
                "ervar", "/org/example/demo3/Reservar.fxml");
        this.rutasFXML.put("Frecuentes", "/org/example/demo3/PreguntasFrecuentes.fxml");
    }

    public static GestorDePantallas obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorDePantallas();
        }
        return instancia;
    }

    private void mostrarPantalla(String rutaFXML, ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            String rutaCompletaFXML = rutasFXML.get(rutaFXML);
            if (rutaCompletaFXML == null) {
                System.err.println("No se encontró la ruta FXML para: " + rutaFXML);
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaCompletaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();  // Muestra la nueva pantalla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarPantallaHome(Stage stage){
        try {
            String rutaCompletaFXML = rutasFXML.get("Home");
            if (rutaCompletaFXML == null) {
                System.err.println("No se encontró la ruta FXML para: " + "Home");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaCompletaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();  // Muestra la nueva pantalla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarPantallaLogin(ActionEvent event){
        mostrarPantalla("Login", event);
    }
    public void mostrarPantallaPostLogin(ActionEvent event){
        mostrarPantalla("PostLogin", event);
    }
    public void mostrarPantallaReservar(ActionEvent event){
        mostrarPantalla("Reservar", event);
    }
    public void mostrarPantallaRegistrar(ActionEvent event){
        mostrarPantalla("Register", event);
    }

    public void mostrarPantallaHome(ActionEvent event) { mostrarPantalla("Home", event); }
    public void mostrarPantallaFrecuentes(ActionEvent event) { mostrarPantalla("Frecuentes", event);}
}
