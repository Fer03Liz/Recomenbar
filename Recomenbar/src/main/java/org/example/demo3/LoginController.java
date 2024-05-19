package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {
    @FXML
    private TextField CorreoField;

    @FXML
    private TextField ContraseñaField;

    @FXML
    private Label textAux;

    public boolean validar(){
        boolean valido = true;
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        if (!logicaDelNegocio.loginRealizado(CorreoField.getText(), ContraseñaField.getText())) {
            valido= false;
            textAux.setText("Correo o contraseña incorrectos");

        }
        return valido;
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException, SQLException {
        textAux.setText("");
        if(validar()){
            Sesion sesion= Sesion.obtenerInstancia();
            sesion.setCorreo(CorreoField.getText());
            GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
            gestorDePantallas.mostrarPantallaPostLogin(event);
        }
    }

    @FXML
    private void onRegistarButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaRegistrar(event);
    }
}
