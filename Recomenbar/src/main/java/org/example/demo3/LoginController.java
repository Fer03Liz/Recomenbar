package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class LoginController {
    private static final Map<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put("usuario1@example.com", "contrasena1");
        usuarios.put("usuario2@example.com", "contrasena2");
        usuarios.put("usuario3@example.com", "contrasena3");
    }

    @FXML
    private TextField CorreoField;

    @FXML
    private TextField ContraseñaField;

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        boolean correo = false;
        boolean contrasena = false;

        for (Map.Entry<String, String> usuario : usuarios.entrySet()) {
            if (CorreoField.getText().equals(usuario.getKey())) {
                correo = true;
                if (ContraseñaField.getText().equals(usuario.getValue())) {
                    contrasena = true;
                }
            }
        }

        if (correo) {
            if (contrasena) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia(stage);
                gestorDePantallas.mostrarPantalla("PostLogin");
            } else {
                ContraseñaField.setPromptText("Contraseña incorrecta");
            }
        } else {
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
