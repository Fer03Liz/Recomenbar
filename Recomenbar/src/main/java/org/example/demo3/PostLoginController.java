package org.example.demo3;

import com.almasb.fxgl.core.util.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class PostLoginController {

    @FXML
    private void onReservarButtonClick(ActionEvent event) throws IOException {
        System.out.println("Reservar Button clicked");
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        System.out.println("Se obtuvo la instancia");
        gestorDePantallas.mostrarPantallaEleccionReservar(event);
    }
    @FXML
    private void onEncuestaButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaEncuesta(event);
    }

    @FXML
    private  void onVerReservarButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = new GestorDePantallas();
        gestorDePantallas.mosrtarPantallaVerReserva(event);
    }

    @FXML
    private void onSalirButtonClick(ActionEvent event) {
        // Cerrar la aplicación
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
