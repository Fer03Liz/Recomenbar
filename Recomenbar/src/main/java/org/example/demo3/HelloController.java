package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private GestorDePantallas gestorDePantallas;

    public void setGestorDePantallas(GestorDePantallas gestorDePantallas) {
        this.gestorDePantallas = gestorDePantallas;
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        // Cargar la nueva pantalla (LoginScreen.fxml)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Login");
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Register");
    }
}
