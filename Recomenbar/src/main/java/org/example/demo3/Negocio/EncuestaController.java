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
    private ChoiceBox<String> choicebox1;
    private ChoiceBox<String> choicebox2;
    private ChoiceBox<String> choicebox3;
    private ChoiceBox<String> choicebox4;



    @FXML
    private void onEncuestaButtonClick(ActionEvent event) throws IOException {
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();

        // Crear una lista de elementos para el ChoiceBox
        ObservableList<String> opuno = FXCollections.observableArrayList(
                "reggaetón",
                "Música electrónica",
                "Rock",
                "House",
                "Salsa",
                "Merengue",
                "Ranchera",
                "Popular",
                "Variado"
        );

        // Crear el ChoiceBox y establecer las opciones
        choicebox1 = new ChoiceBox<>(opuno);

        ObservableList<String> opdos = FXCollections.observableArrayList(
                "Chapinero",
                "85",
                "Restrepo",
                "Modelia",
                "Usaquen",
                "La candelaria"
        );
        choicebox2 = new ChoiceBox<>(opdos);

        ObservableList<String> optres = FXCollections.observableArrayList(
                "10-15k",
                "20-25k",
                "25-30k",
                "30-25k",
                "+40k",
                "sin cover",
                "no importa cuanto"
        );
        choicebox3 = new ChoiceBox<>(optres);

        ObservableList<String> opcuatro = FXCollections.observableArrayList(
                "Bailar",
                "Comer y Bailar",
                "Cocteles y hablar",
                "Musica en vivo",
                "Un poco de todo"
        );
        choicebox4 = new ChoiceBox<>(opcuatro);

        // Establecer el primer elemento como seleccionado por defecto
        choicebox1.getSelectionModel().selectFirst();
        choicebox2.getSelectionModel().selectFirst();
        choicebox3.getSelectionModel().selectFirst();
        choicebox4.getSelectionModel().selectFirst();
    }

    private void mostrarPantalla(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaEncuesta(event);
    }
}

