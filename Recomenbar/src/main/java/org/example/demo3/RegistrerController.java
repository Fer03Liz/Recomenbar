package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    private Label TextAux1;

    @FXML
    private Label TextAux2;

    @FXML
    private Label TextAux3;

    @FXML
    private Label TextAux4;

    @FXML
    private void onVolverButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaLogin(event);
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

    public boolean validarCampos(){
        boolean valido = true;
        Usuario usuario= new Usuario(nombresField.getText(),correoField.getText(),0,contraseñaField.getText());
        if(usuario.getNombre().isEmpty() || usuario.getNombre()==null){
            valido = false;
            System.out.println("El nombre no puede ser vacio");
            TextAux1.setText("El nombre no puede ser vacio");
        }
        if (usuario.getCorreo().isEmpty() || usuario.getCorreo()==null){
            valido = false;
            System.out.println("El correo no puede ser vacio");
            TextAux3.setText("El correo no puede ser vacio");
        }
        if(usuario.getContraseña().isEmpty() || usuario.getContraseña()==null){
            valido = false;
            System.out.println("La contraseña no puede ser vacia");
            TextAux4.setText("La contraseña no puede ser vacia");
        }
        if (!usuario.getCorreo().contains("@") || !usuario.getCorreo().contains(".com")){
            valido = false;
            System.out.println("El correo no es valido");
            TextAux3.setText("El correo no es valido");
        }
        if (!esNumerico(edadField.getText())){
            valido = false;
            System.out.println("La edad no puede ser vacio");
            TextAux2.setText("La edad no puede ser vacio");
        }
        int edad = Integer.parseInt(edadField.getText());
        usuario.setEdad(edad);
        if(usuario.getEdad()<18){
            valido = false;
            System.out.println("La edad no puede ser menor de 18");
            TextAux2.setText("La edad no puede ser menor de 18");
        }
        return valido;
    }

    @FXML
    private void onRegitrarButtonClick(ActionEvent event) throws IOException, SQLException {//CORREGIR PRUEBA REGISTRARSE CON CORREO YA EXISTENTE
        if(validarCampos()){
            TextAux1.setText("");
            TextAux2.setText("");
            TextAux3.setText("");
            TextAux4.setText("");

            // Lógica insertar usuario:
            int edad = Integer.parseInt(edadField.getText());
            Usuario usuario= new Usuario(nombresField.getText(),correoField.getText(),edad,contraseñaField.getText());
            LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
            if(logicaDelNegocio.registrarUsuario(usuario.getNombre(), usuario.getEdad() ,usuario.getCorreo(), usuario.getContraseña())) {
                Sesion sesion= Sesion.obtenerInstancia();
                sesion.setCorreo(usuario.getCorreo());
                GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
                gestorDePantallas.mostrarPantallaPostLogin(event);
            }
        }
    }

}
