package org.example.demo3.Negocio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.demo3.GestorDePantallas;
import javafx.stage.Stage;
import java.io.IOException;

public class EncuestaController {

    @FXML
    private TextField preguntauno;
    private TextField preguntados;
    private TextField preguntatres;
    private TextField preguntacuatro;


    @FXML
    private TextField ContraseñaField;


    @FXML
    private void onEncuestaButtonClick(ActionEvent event) throws IOException {


        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
       public void start( Stage PrimaryStage) {
            // Crear una lista de elementos para el ChoiceBox
            ObservableList<String> opciones = FXCollections.observableArrayList(
                    "Opción 1",
                    "Opción 2",
                    "Opción 3"
            );

            // Crear el ChoiceBox y establecer las opciones
            ChoiceBox<String> choiceBox = new ChoiceBox<>(opciones);

            // Establecer el primer elemento como seleccionado por defecto
            choiceBox.getSelectionModel().selectFirst();

            // Crear un layout y añadir el ChoiceBox
            VBox root = new VBox(choiceBox);

            // Crear la escena y añadir el layout
            Scene scene = new Scene(root, 300, 200);

            // Configurar el escenario y mostrarlo
            primaryStage1.setTitle("Ejemplo de ChoiceBox");
            primaryStage1.setScene(scene);
            primaryStage1.show();
        }
    }
    @FXML
    private void onRegistarButtonClick(ActionEvent event) throws IOException {
        mostrarPantalla(3, event);
    }

    private void mostrarPantalla(int pantalla, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia(stage);
        gestorDePantallas.seleccionarPantalla(pantalla);
    }
}

