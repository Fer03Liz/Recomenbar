package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PreReservaController {

    @FXML
    private void onDiscotecaButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaReservarDiscoteca(event);
    }

    @FXML
    private void onEventoButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaReservarEvento(event);
    }
}
