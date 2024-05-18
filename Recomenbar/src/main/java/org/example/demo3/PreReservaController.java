package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Negocio.LogicaDelNegocio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PreReservaController {

    @FXML
    private void onDiscotecaButtonClick(ActionEvent event) throws IOException, SQLException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        List<Discoteca> discotecas = logicaDelNegocio.disponibles();
        gestorDePantallas.mostrarPantallaReservarDiscoteca(event, discotecas);
    }

    @FXML
    private void onEventoButtonClick(ActionEvent event) throws IOException {
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaReservarEvento(event);
    }
}
