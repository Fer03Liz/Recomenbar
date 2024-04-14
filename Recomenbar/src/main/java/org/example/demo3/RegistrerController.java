package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo3.Entidades.Usuario;

import java.io.IOException;

public class RegistrerController {

    @FXML
    private TextField nombresField;

    @FXML
    private TextField edadField;

    @FXML
    private TextField correoField;

    @FXML
    private PasswordField contraseñaField;

    @FXML
    private Button registrarButton;

    @FXML
    private Button volverButton;

    @FXML
    private void onVolverButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Login");
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Home");
    }

    @FXML
    private void onRegitrarButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        String nombres = nombresField.getText();
        String correo = correoField.getText();
        String contraseña = contraseñaField.getText();
        String edadText = edadField.getText();
        if (!edadText.isEmpty()) {
            int edad = Integer.parseInt(edadText);
            if(!nombres.isEmpty()&&!correo.isEmpty()&&!contraseña.isEmpty()){
                //System.out.println("ENTRO");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
                gestorDePantallas.mostrarPantalla("Reservar");
            }else if(nombres.isEmpty()){
                nombresField.setPromptText("Ingrese un nombre");
            }else if(correo.isEmpty()){
                correoField.setPromptText("Ingrese un correo");
            } else if (contraseña.isEmpty()) {
                contraseñaField.setPromptText("Ingrese un contraseña");
            }
        } else {
            edadField.setPromptText("Digita un valor numerico");
        }
    }
}
