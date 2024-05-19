package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import java.io.IOException;
import java.sql.SQLException;
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
        this.rutasFXML.put("Registrar", "/org/example/demo3/Registrar.fxml");
        this.rutasFXML.put("ReservarDiscoteca", "/org/example/demo3/ReservarDiscoteca.fxml");
        this.rutasFXML.put("EleccionReservar", "/org/example/demo3/PreReserva.fxml");
        this.rutasFXML.put("ReservarEvento", "/org/example/demo3/ReservarEvento.fxml");
        this.rutasFXML.put("Frecuentes","/org/example/demo3/PreguntasFrecuentes.fxml");
        this.rutasFXML.put("Encuesta", "/org/example/demo3/Encuesta.fxml");
        this.rutasFXML.put("VerReserva","/org/example/demo3/VerReserva.fxml");
        this.rutasFXML.put("VerInfoReserva","/org/example/demo3/VerInfoReserva.fxml");
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

    private void mostrarPantalla(String rutaFXML, ActionEvent event, Reserva reserva ) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String rutaCompletaFXML = rutasFXML.get(rutaFXML);
        if (rutaCompletaFXML == null) {
            System.err.println("No se encontró la ruta FXML para: " + rutaFXML);
            throw new IOException("No se encontró la ruta FXML para: " + rutaFXML);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaCompletaFXML));
        Parent root = loader.load();

        // Obtener el controlador y pasar las discotecas
        if (reserva != null && rutaFXML.equals("VerInfoReserva")) {
            VerInfoReservaController controller = loader.getController();
            controller.setReserva(reserva);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void mostrarVerInfoReserva(ActionEvent event, Reserva reserva) throws IOException, SQLException {mostrarPantalla("VerInfoReserva",event,reserva);}

    public void mostrarPantallaPostLogin(ActionEvent event) throws IOException, SQLException {
        Sesion sesion= Sesion.obtenerInstancia();
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Usuario usuario= logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
        if(usuario.getTipo()==1){
            mostrarPantalla("PostLogin", event);
        }else{

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
            stage.setFullScreen(true);
            stage.centerOnScreen();
            stage.show();  // Muestra la nueva pantalla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarPantallaLogin(ActionEvent event) throws IOException {mostrarPantalla("Login", event);}
    public void mostrarPantallaRegistrar(ActionEvent event) throws IOException {mostrarPantalla("Registrar", event);}
    public void mostrarPantallaEleccionReservar(ActionEvent event) throws IOException {mostrarPantalla("EleccionReservar", event);}
    public void mostrarPantallaReservarEvento(ActionEvent event) throws IOException {mostrarPantalla("ReservarEvento", event);}
    public void mostrarPantallaFrecuentes(ActionEvent event) throws  IOException{mostrarPantalla("Frecuentes",event);}
    public void mostrarPantallaEncuesta(ActionEvent event) throws  IOException {mostrarPantalla("Encuesta", event);}
    public void mosrtarPantallaVerReserva(ActionEvent event) throws IOException{mostrarPantalla("VerReserva", event);}
}
