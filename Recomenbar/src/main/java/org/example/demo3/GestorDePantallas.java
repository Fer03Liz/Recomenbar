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
        this.rutasFXML.put("ReservarDiscoteca", "/org/example/demo3/ReservarDiscoteca.fxml");
        this.rutasFXML.put("EleccionReservar", "/org/example/demo3/PreReserva.fxml");
        this.rutasFXML.put("ReservarEvento", "/org/example/demo3/ReservarEvento.fxml");
        this.rutasFXML.put("Frecuentes","/org/example/demo3/PreguntasFrecuentes.fxml");
    }

    public static GestorDePantallas obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorDePantallas();
        }
        return instancia;
    }

    private void mostrarPantalla(String rutaFXML, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String rutaCompletaFXML = rutasFXML.get(rutaFXML);
        if (rutaCompletaFXML == null) {
            System.err.println("No se encontró la ruta FXML para: " + rutaFXML);
            throw new IOException("No se encontró la ruta FXML para: " + rutaFXML);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaCompletaFXML));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();  // Muestra la nueva pantalla
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
    public void mostrarPantallaLogin(ActionEvent event) throws IOException {mostrarPantalla("Login", event);}
    public void mostrarPantallaPostLogin(ActionEvent event) throws IOException {mostrarPantalla("PostLogin", event);}
    public void mostrarPantallaReservarDiscoteca(ActionEvent event) throws IOException {mostrarPantalla("ReservarDiscoteca", event);}
    public void mostrarPantallaRegistrar(ActionEvent event) throws IOException {mostrarPantalla("Register", event);}
    public void mostrarPantallaEleccionReservar(ActionEvent event) throws IOException {mostrarPantalla("EleccionReservar", event);}
    public void mostrarPantallaReservarEvento(ActionEvent event) throws IOException {mostrarPantalla("ReservarEvento", event);}
    public void mostrarPantallaFrecuentes(ActionEvent event) throws  IOException{mostrarPantalla("Frecuentes",event);}
}
