package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo3.Negocio.LogicaDelNegocio;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField CorreoField;

    @FXML
    private TextField ContraseñaField;

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
                if(logicaDelNegocio.loginRealizado(CorreoField.getText(),ContraseñaField.getText())){
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia(stage);
                    gestorDePantallas.mostrarPantalla("PostLogin");
                }else{
                    ContraseñaField.setPromptText("Contraseña incorrecta");
                    CorreoField.setPromptText("Usuario incorrecta");
                }
    }

    @FXML
    private void onRegistarButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia(stage);
        gestorDePantallas.mostrarPantalla("Register");
    }
}
