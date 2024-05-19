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
               // System.out.println(evento.getNombre());
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
        /*for( Reserva r: reservas){
            Discoteca discoteca = logicaDelNegocio.discotecaID(r.getIdDiscoteca());
            //System.out.println(discoteca.getNombre());
        }*/
        return reservas;
    }

    private boolean validar() throws SQLException {
        boolean validar = true;
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        String discotecaSeleccionada = ListViewDiscotecas.getSelectionModel().getSelectedItem();
        String eventoSeleccionado = ListViewEventos.getSelectionModel().getSelectedItem();

        System.out.println("Discoteca: " + discotecaSeleccionada);
        System.out.println("Evento: " + eventoSeleccionado);

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
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Reserva reservaEscogida= null;
        String discotecaSeleccionada = ListViewDiscotecas.getSelectionModel().getSelectedItem();
        Discoteca discoteca = logicaDelNegocio.discotecaNombre(discotecaSeleccionada);
        String eventoSeleccionado = ListViewEventos.getSelectionModel().getSelectedItem();
        Evento evento = logicaDelNegocio.eventoNombre(eventoSeleccionado);
        List<Reserva> reservas = null;
        System.out.println("BOTON OPRIMIDO");
        try {
            reservas = obtenerReservasDisponibles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(validar());
        if(validar()){
            for( Reserva r: reservas){
                if(r.getIdDiscoteca()==discoteca.getId()){
                    reservaEscogida=r;
                }
                if(r.getIdEvento()==evento.getId()){
                    reservaEscogida=r;
                }
            }
            Discoteca discotecaReservada= logicaDelNegocio.discotecaID(reservaEscogida.getIdDiscoteca());
            System.out.println(discotecaReservada.getNombre());
            GestorDePantallas gestorDePantallas= GestorDePantallas.obtenerInstancia();
            gestorDePantallas.mostrarVerInfoReserva(event, reservaEscogida);
        }
    }

}