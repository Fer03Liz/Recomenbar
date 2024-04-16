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
import org.example.demo3.Negocio.LogicaDelNegocio;

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
    private void onVolverButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
        gestorDePantallas.mostrarPantalla("Login");
    }

    public static boolean esNumerico(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void onRegitrarButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        String nombres = nombresField.getText();
        String correo = correoField.getText();
        String contraseña = contraseñaField.getText();
        String edadText = edadField.getText();
            if(!nombres.isEmpty()&&!correo.isEmpty()&&!contraseña.isEmpty()&&!edadText.isEmpty()){
                if(correo.contains("@")&&correo.contains(".com")) {
                    if (esNumerico(edadText)) {
                        int edad = Integer.parseInt(edadText);
                        if(edad>=18){
                            //Logica insertar usuario:

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            GestorDePantallas gestorDePantallas = new GestorDePantallas(stage);
                            gestorDePantallas.mostrarPantalla("Reservar");
                        } else{
                            edadField.clear();
                            edadField.setPromptText("No cumples con el requisito de edad");
                        }
                    } else {
                        edadField.clear();
                        edadField.setPromptText("Digita un valor numerico");
                    }
                }else{
                    correoField.clear();
                    correoField.setPromptText("Digita un correo valido");
                }
            }else if(nombres.isEmpty()){
                nombresField.setPromptText("Ingrese un nombre");
            }else if(correo.isEmpty()){
                correoField.setPromptText("Ingrese un correo");
            } else if (contraseña.isEmpty()) {
                contraseñaField.setPromptText("Ingrese un contraseña");
            }else if (edadText.isEmpty()) {
                contraseñaField.setPromptText("Ingrese su edad");
            }
    }
}
