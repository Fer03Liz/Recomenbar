package org.example.demo3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Evento;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VerReservaController implements Initializable {

    @FXML
    private ListView<String> ListViewDiscotecas;

    @FXML
    private ListView<String> ListViewEventos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Inicializar la lista de nombres de bares
            List<Reserva> reservas = obtenerReservasDisponibles();
            LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();

            // Llenar la ListView con los nombres de los bares
            for (Reserva reserva : reservas) {
                Evento evento = logicaDelNegocio.eventoIdEvento(reserva.getIdEvento());
                if (evento.isPrivado()) {
                    Discoteca discoteca = logicaDelNegocio.discotecaID(reserva.getIdDiscoteca());
                    ListViewDiscotecas.getItems().add(discoteca.getNombre());
                } else {
                    ListViewEventos.getItems().add(evento.getNombre());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Reserva> obtenerReservasDisponibles() throws SQLException {
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        Sesion sesion = Sesion.obtenerInstancia();
        Usuario usuario = logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
        return logicaDelNegocio.reservasValidas(usuario.getId());
    }

    private boolean validar() throws SQLException {
        boolean validar = true;
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        String discotecaSeleccionada = ListViewDiscotecas.getSelectionModel().getSelectedItem();
        String eventoSeleccionado = ListViewEventos.getSelectionModel().getSelectedItem();

        Discoteca discoteca = null;
        Evento evento = null;

        if (discotecaSeleccionada == null && eventoSeleccionado == null) {
            validar = false;
        }

        if (discotecaSeleccionada != null) {
            discoteca = logicaDelNegocio.discotecaNombre(discotecaSeleccionada);
        }
        if (eventoSeleccionado != null) {
            evento = logicaDelNegocio.eventoNombre(eventoSeleccionado);
        }

        if (discoteca != null && evento != null) {
            validar = false;
            ListViewDiscotecas.getSelectionModel().clearSelection();
            ListViewEventos.getSelectionModel().clearSelection();
        }

        return validar;
    }

    @FXML
    private void onVerInfoReserva(ActionEvent event) throws SQLException, IOException {
        if (!validar()) {
            System.out.println("Validación fallida: Selecciona una discoteca o un evento.");
            return;
        }

        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        Reserva reservaEscogida = null;
        String discotecaSeleccionada = ListViewDiscotecas.getSelectionModel().getSelectedItem();
        String eventoSeleccionado = ListViewEventos.getSelectionModel().getSelectedItem();

        Discoteca discoteca = null;
        Evento evento = null;

        if (discotecaSeleccionada != null) {
            discoteca = logicaDelNegocio.discotecaNombre(discotecaSeleccionada);
        }
        if (eventoSeleccionado != null) {
            evento = logicaDelNegocio.eventoNombre(eventoSeleccionado);
        }

        List<Reserva> reservas = obtenerReservasDisponibles();

        if (discoteca != null) {
            for (Reserva r : reservas) {
                if (r.getIdDiscoteca() == discoteca.getId()) {
                    reservaEscogida = r;
                    break;
                }
            }
        } else if (evento != null) {
            for (Reserva r : reservas) {
                if (r.getIdEvento() == evento.getId()) {
                    reservaEscogida = r;
                    break;
                }
            }
        }

        if (reservaEscogida != null) {
            GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
            gestorDePantallas.mostrarVerInfoReserva(event, reservaEscogida);
        } else {
            System.out.println("No se encontró una reserva correspondiente.");
        }
    }
}
