package org.example.demo3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestorDePantallas {
    private static GestorDePantallas instancia;
    private final Map<String, String> rutasFXML;
    private final Stage escenarioPrincipal;

    GestorDePantallas(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.rutasFXML = new HashMap<>();
        this.rutasFXML.put("Home", "/org/example/demo3/Home.fxml");
        this.rutasFXML.put("Login", "/org/example/demo3/Login.fxml");
        this.rutasFXML.put("PostLogin", "/org/example/demo3/PostLogin.fxml");
        this.rutasFXML.put("Register", "/org/example/demo3/Registrer.fxml");
        this.rutasFXML.put("Reservar", "/org/example/demo3/Reservar.fxml");
        this.rutasFXML.put("Encuesta", "/org/example/demo3/Encuesta.fxml");
        this.rutasFXML.put("Eleccion", "/org/example/demo3/Encuesta.fxml");
    }

    public static GestorDePantallas obtenerInstancia(Stage escenarioPrincipal) {
        if (instancia == null) {
            instancia = new GestorDePantallas(escenarioPrincipal);
        }
        return instancia;
    }

    private void mostrarPantalla(String rutaFXML) {
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

    private void mostrarPantallaHome(){
        mostrarPantalla("Home");
    }
    private void mostrarPantallaLogin(){
        mostrarPantalla("Login");
    }
    private void mostrarPantallaPostLogin(){
        mostrarPantalla("PostLogin");
    }
    private void mostrarPantallaReservar(){
        mostrarPantalla("Reservar");
    }
    private void mostrarPantallaRegistrar(){mostrarPantalla("Register");}
    private void mostrarPantallaEncuesta(){mostrarPantalla("Encuesta");}

    public void seleccionarPantalla(int numeroPantalla){
        if(numeroPantalla==0){
            mostrarPantallaHome();
        }else if(numeroPantalla==1){
            mostrarPantallaLogin();
        }else if (numeroPantalla==2) {
            mostrarPantallaPostLogin();
        }else if(numeroPantalla==3){
            mostrarPantallaRegistrar();
        }else if(numeroPantalla==4){
            mostrarPantallaReservar();
        }
        else if(numeroPantalla==5)
        {
            mostrarPantallaEncuesta();
        }

    }
}
