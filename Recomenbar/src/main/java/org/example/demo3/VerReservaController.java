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
    private ListView<String> ListViewDiscotecas;

    @FXML
    private ListView<String> ListViewEventos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar la lista de nombres de bares
        List<Reserva> reservas = null;
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        try {
            reservas = obtenerReservasDisponibles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Llenar la ListView con los nombres de los bares
        for (Reserva reserva : reservas) {
            Discoteca discoteca = new Discoteca();
            try {
                Evento evento = logicaDelNegocio.eventoIdEvento(reserva.getIdEvento());
                System.out.println(evento.getNombre());
                if(evento.isPrivado()){
                    discoteca= logicaDelNegocio.discotecaID(reserva.getIdDiscoteca());
                    ListViewDiscotecas.getItems().addAll(discoteca.getNombre());
                }else{
                    ListViewEventos.getItems().addAll(evento.getNombre());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Reserva> obtenerReservasDisponibles() throws SQLException {
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Sesion sesion = Sesion.obtenerInstancia();
        Usuario usuaro= logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
        List<Reserva> reservas = logicaDelNegocio.reservasValidas(usuaro.getId());
        for( Reserva r: reservas){
            Discoteca discoteca = logicaDelNegocio.discotecaID(r.getIdDiscoteca());
            System.out.println(discoteca.getNombre());
        }
        return reservas;
    }
}