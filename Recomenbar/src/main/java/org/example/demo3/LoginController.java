package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class LoginController {
    private Map<String, String> usuarios= new HashMap<>();
    public LoginController() {
        usuarios.put("usuario1@example.com", "contrasena1");
        usuarios.put("usuario2@example.com", "contrasena2");
        usuarios.put("usuario3@example.com", "contrasena3");
    }

    @FXML
    private TextField CorreoField;

    @FXML
    private TextField ContraseñaField;
    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();

        // Obtener la escena actual y el stage asociado
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        // Cerrar la pantalla actual
        stage.close();

        // Mostrar la nueva pantalla
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.show();
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
    new LoginController();
    boolean correo= false;
    boolean contrasena = false;

    for(Map.Entry<String, String> usuario : usuarios.entrySet()){
        if(CorreoField.getText().equals(usuario.getValue())){
            correo = true;
            if(ContraseñaField.getText().equals(usuario.getValue())){
              contrasena = true;
            }
        }
    }

    if(correo){
        if(contrasena){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostLogin.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el stage asociado
            Scene scene = ((Node) event.getSource()).getScene();
            Stage stage = (Stage) scene.getWindow();
            // Cerrar la pantalla actual
            stage.close();
            // Mostrar la nueva pantalla
            stage.setScene(new Scene(root));
            stage.setTitle("Preview");
            stage.show();

        }else{
            ContraseñaField.setPromptText("Contraseña incorrecta");
        }
    }else{
        CorreoField.setPromptText("Usuario incorrecta");
    }

        //System.out.println("ENTRO");

    }

    @FXML
    private void onRegistarButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registrer.fxml"));
        Parent root = loader.load();

        // Obtener la escena actual y el stage asociado
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        // Cerrar la pantalla actual
        stage.close();

        // Mostrar la nueva pantalla
        stage.setScene(new Scene(root));
        stage.setTitle("Registrar");
        stage.show();
    }
}
