package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class PostLoginController {

    @FXML
    private void onReservarButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia(stage);
        gestorDePantallas.mostrarPantalla("Reservar");
    }

}
