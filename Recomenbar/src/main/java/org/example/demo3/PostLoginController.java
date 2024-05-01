package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class PostLoginController {

    @FXML
    private void onReservarButtonClick(ActionEvent event) throws IOException {
        mostrarPantalla(4, event);
    }
    @FXML
    private void onEncuestaButtonClick(ActionEvent event) throws IOException {
        mostrarPantalla(5, event);
    }

    private void mostrarPantalla(int pantalla, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia(stage);
        gestorDePantallas.seleccionarPantalla(pantalla);
    }
}
