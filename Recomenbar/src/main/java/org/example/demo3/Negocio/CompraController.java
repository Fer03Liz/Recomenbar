package org.example.demo3.Negocio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.demo3.Entidades.*;

import java.sql.SQLException;
import java.util.Date;

public class CompraController {

    @FXML
    private Label nombreUsuario;

    @FXML
    private Label nombreDiscoteca;

    @FXML
    private Label numeroPersonas;

    @FXML
    private Label nombreEvento;

    @FXML
    private Label textEvento;

    @FXML
    private Label fecha;

    @FXML
    private Label precio;

    public void setReservaConPrecio(Reserva reserva) throws SQLException {
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Sesion sesion= Sesion.obtenerInstancia();
        Usuario usuario= logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
        int id= reserva.getId();
        int entrada =logicaDelNegocio.idEntrada(reserva.getId());
        precio.setText(String.valueOf(entrada));
        nombreUsuario.setText(usuario.getNombre());
        Discoteca discoteca= logicaDelNegocio.discotecaID(reserva.getIdDiscoteca());
        nombreDiscoteca.setText(discoteca.getNombre());
        numeroPersonas.setText(reserva.getCantEntradas()+" Personas");
        Evento evento= logicaDelNegocio.eventoIdEvento(reserva.getIdEvento());
        if(evento.isPrivado()){
            nombreEvento.setText("");
            textEvento.setText("");
        }else{
            nombreEvento.setText(evento.getNombre());
            textEvento.setText("Para el evento: ");
        }

        Date fechaD= reserva.getFecha();
        fecha.setText(fechaD.toString());
    }




}
