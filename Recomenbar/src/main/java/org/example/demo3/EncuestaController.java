package org.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Negocio.LogicaDelNegocio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EncuestaController implements Initializable {

    @FXML
    private ChoiceBox<String> choicebox1;
    @FXML
    private ChoiceBox<String> choicebox2;
    @FXML
    private ChoiceBox<String> choicebox3;
    @FXML
    private ChoiceBox<String> choicebox4;
    @FXML
    private ListView<String> listViewBares;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear una lista de elementos para cada ChoiceBox
        ObservableList<String> opuno = FXCollections.observableArrayList(
                "Regueton",
                "Electrónica",
                "Rock",
                "House",
                "Salsa",
                "Merengue",
                "Rancheras",
                "Popular",
                "Variedad"
        );

        ObservableList<String> opdos = FXCollections.observableArrayList(
                "Chapinero",
                "Calle 85",
                "Calle 116",
                "Chia"
        );

        ObservableList<String> optres = FXCollections.observableArrayList(
                "15.000",
                "20.000",
                "25.000",
                "30.000",
                "35.000",
                "40.000",
                "45.000",
                "50.000"
        );

        ObservableList<String> opcuatro = FXCollections.observableArrayList(
                "Bar",
                "Gastrobar"
        );

        // Establecer las opciones para cada ChoiceBox
        choicebox1.setItems(opuno);
        choicebox2.setItems(opdos);
        choicebox3.setItems(optres);
        choicebox4.setItems(opcuatro);

        // Inicializar la lista de nombres de bares
        try {
            actualizarListaBares();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void actualizarListaBares() throws SQLException {
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        List<Discoteca> discotecas = logicaDelNegocio.filtrarDiscotecas(
                choicebox1.getValue(),
                choicebox2.getValue(),
                choicebox3.getValue(),
                choicebox4.getValue()
        );

        // Limpiar la lista actual de nombres de bares
        listViewBares.getItems().clear();

        if (discotecas.isEmpty()) {
            // No hay discotecas que cumplan con las características
            listViewBares.getItems().add("No hay una discoteca que cumpla con las características.");
        } else {
            // Llenar la ListView con los nombres de los bares filtrados
            for (Discoteca d : discotecas) {
                listViewBares.getItems().add(d.getNombre());
            }
        }
    }

    @FXML
    private void onResultadosButtonClick(ActionEvent event) {
        try {
            actualizarListaBares();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onReservarButtonClick(ActionEvent event) throws SQLException, IOException {
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        List<Discoteca> discotecas = logicaDelNegocio.filtrarDiscotecas(
                choicebox1.getValue(),
                choicebox2.getValue(),
                choicebox3.getValue(),
                choicebox4.getValue()
        );
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();

        if (discotecas != null && !discotecas.isEmpty()) {
            gestorDePantallas.mostrarPantallaReservarDiscoteca(event, discotecas);
        } else {
            discotecas = logicaDelNegocio.disponibles();
            gestorDePantallas.mostrarPantallaReservarDiscoteca(event, discotecas);
        }
    }
}
