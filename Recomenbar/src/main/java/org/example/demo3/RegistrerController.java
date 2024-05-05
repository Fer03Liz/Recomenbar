package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import java.io.IOException;
import java.sql.SQLException;

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
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaPostLogin(event);
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
    private void onRegitrarButtonClick(ActionEvent event) throws IOException, SQLException {//CORREGIR PRUEBA REGISTRARSE CON CORREO YA EXISTENTE
        Usuario usuario= new Usuario(nombresField.getText(),correoField.getText(),0,contraseñaField.getText());
        if (!usuario.getNombre().isEmpty() && !usuario.getCorreo().isEmpty() && !usuario.getContraseña().isEmpty() && !edadField.getText().isEmpty()) {
            if (usuario.getCorreo().contains("@") && usuario.getCorreo().contains(".com")) {
                if (esNumerico(edadField.getText())) {
                    int edad = Integer.parseInt(edadField.getText());
                    usuario.setEdad(edad);
                    if (usuario.getEdad() >= 18) {
                        // Lógica insertar usuario:
                        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
                        if(logicaDelNegocio.registrarUsuario(usuario.getNombre(),usuario.getCorreo(), usuario.getEdad(), usuario.getContraseña())) {
                            Sesion sesion= Sesion.obtenerInstancia();
                            sesion.setCorreo(usuario.getCorreo());
                            GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
                            gestorDePantallas.mostrarPantallaPostLogin(event);
                        }
                    } else {
                        edadField.clear();
                        edadField.setPromptText("No cumples con el requisito de edad");
                    }
                } else {
                    edadField.clear();
                    edadField.setPromptText("Digita un valor numerico");
                }
            } else {
                correoField.clear();
                correoField.setPromptText("Digita un correo valido");
            }
        } else if (nombresField.getText().isEmpty()) {
            nombresField.setPromptText("Ingrese un nombre");
        } else if (correoField.getText().isEmpty()) {
            correoField.setPromptText("Ingrese un correo");
        } else if (contraseñaField.getText().isEmpty()) {
            contraseñaField.setPromptText("Ingrese un contraseña");
        } else if (edadField.getText().isEmpty()) {
            contraseñaField.setPromptText("Ingrese su edad");
        }
    }

}
