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
    @FXML
    private void onEncuestaButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaEncuesta(event);
    }

    @FXML
    private  void onVerReservaClick(ActionEvent event) throws  IOException{
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaVerReserva(event);
    }
}
