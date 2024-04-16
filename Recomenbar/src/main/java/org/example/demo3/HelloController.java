package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.example.demo3.Negocio.LogicaDelNegocio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {

    private GestorDePantallas gestorDePantallas;

    public void setGestorDePantallas(GestorDePantallas gestorDePantallas) {
        this.gestorDePantallas = gestorDePantallas;
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Login");
        LogicaDelNegocio logicaDelNegocio=new LogicaDelNegocio();
        logicaDelNegocio.loginRealizado();
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) throws SQLException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Register");

    }
}
