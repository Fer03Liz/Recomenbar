package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class HelloController {

    private GestorDePantallas gestorDePantallas;

    public void setGestorDePantallas(GestorDePantallas gestorDePantallas) {
        this.gestorDePantallas = gestorDePantallas;
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        if (gestorDePantallas != null) {
            gestorDePantallas.mostrarPantalla("Login");
        } else {
            System.err.println("El gestorDePantallas no está inicializado correctamente.");
        }
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) {
        if (gestorDePantallas != null) {
            gestorDePantallas.mostrarPantalla("Register");
        } else {
            System.err.println("El gestorDePantallas no está inicializado correctamente.");
        }
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        if (gestorDePantallas != null) {
            gestorDePantallas.mostrarPantalla("Home");
        } else {
            System.err.println("El gestorDePantallas no está inicializado correctamente.");
        }
    }
}
