package org.example.demo3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class FrecuentesController {

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaLogin(event);
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaRegistrar(event);
    }
}