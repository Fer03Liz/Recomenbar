package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PostLoginController {

    @FXML
    private void onReservarButtonClick(ActionEvent event) throws IOException {
        System.out.println("Reservar Button clicked");
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        System.out.println("Se obtuvo la instancia");
        gestorDePantallas.mostrarPantallaEleccionReservar(event);
    }

}
