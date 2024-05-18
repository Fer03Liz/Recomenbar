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

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VerReservaController implements Initializable {

    @FXML
    private ListView<String> listViewtusreservas;

    @FXML
    private ListView<String> listViewtusreservasEvento;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar la lista de reservas del usuario
        List<Reserva> reservas= null;
        List<Evento> eventos;
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        try {
            reservas = obtenerReservasDisponibles();
            // Llenar la ListView con los nombres de las discotecas correspondientes a las reservas
            for (Reserva reserva : reservas) {
                Discoteca discoteca = logicaDelNegocio.discotecaID(reserva.getIdDiscoteca());
                listViewtusreservas.getItems().add(discoteca.getNombre());
            }
            for(Reserva reserva : reservas){
                Evento evento= logicaDelNegocio.eventoIdEvento(reserva.getIdEvento());
                if(!evento.isPrivado()){
                    listViewtusreservasEvento.getItems().add(evento.getNombre());
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
}
