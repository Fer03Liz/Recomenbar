package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo3.Entidades.Discoteca;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
        this.rutasFXML.put("Encuesta", "/org/example/demo3/Encuesta.fxml");
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
        stage.centerOnScreen();
        stage.show();  // Muestra la nueva pantalla
    }
    private void mostrarPantalla(String rutaFXML, ActionEvent event, List<Discoteca> discotecas) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String rutaCompletaFXML = rutasFXML.get(rutaFXML);
        if (rutaCompletaFXML == null) {
            System.err.println("No se encontró la ruta FXML para: " + rutaFXML);
            throw new IOException("No se encontró la ruta FXML para: " + rutaFXML);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaCompletaFXML));
        Parent root = loader.load();

        // Obtener el controlador y pasar las discotecas
        if (discotecas != null && rutaFXML.equals("ReservarDiscoteca")) {
            ReservarDiscotecaController controller = loader.getController();
            controller.setDiscotecas(discotecas);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void mostrarPantallaReservarDiscoteca(ActionEvent event, List<Discoteca> discotecas) throws IOException {mostrarPantalla("ReservarDiscoteca", event, discotecas);}

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
            stage.setFullScreen(true);
            stage.centerOnScreen();
            stage.show();  // Muestra la nueva pantalla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarPantallaLogin(ActionEvent event) throws IOException {mostrarPantalla("Login", event);}
    public void mostrarPantallaPostLogin(ActionEvent event) throws IOException {mostrarPantalla("PostLogin", event);}
    public void mostrarPantallaRegistrar(ActionEvent event) throws IOException {mostrarPantalla("Register", event);}
    public void mostrarPantallaEleccionReservar(ActionEvent event) throws IOException {mostrarPantalla("EleccionReservar", event);}
    public void mostrarPantallaReservarEvento(ActionEvent event) throws IOException {mostrarPantalla("ReservarEvento", event);}
    public void mostrarPantallaFrecuentes(ActionEvent event) throws  IOException{mostrarPantalla("Frecuentes",event);}
    public void mostrarPantallaEncuesta(ActionEvent event) throws  IOException {mostrarPantalla("Encuesta", event);}
}
