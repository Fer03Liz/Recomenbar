package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Evento;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    private ImageView QR;

    @FXML
    private Button verReservaButton;

    @FXML
    private Label precio;

    private Reserva reservita;

    public void setReserva(Reserva reserva) throws SQLException {

        reservita=reserva;
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Sesion sesion= Sesion.obtenerInstancia();
        Usuario usuario= logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
        nombreUsuario.setText(usuario.getNombre());
        Discoteca discoteca= logicaDelNegocio.discotecaID(reserva.getIdDiscoteca());
        nombreDiscoteca.setText(discoteca.getNombre());
        numeroPersonas.setText(reserva.getCantEntradas()+" Personas");
        Evento evento= logicaDelNegocio.eventoIdEvento(reserva.getIdEvento());
        if(evento!=null) {
            if (evento.isPrivado()) {
                nombreEvento.setText("El evento es Privado");

            } else {
                nombreEvento.setText("El evento es: "+evento.getNombre());

            }
        }
        Date fechaD= reserva.getFecha();
        fecha.setText(fechaD.toString());

        // Obtener el dato BLOB de la base de datos
        byte[] qrCodeData = logicaDelNegocio.obtenerImagenQR(reserva.getIdEntrada());

        // Convertir el dato BLOB en una imagen y establecerla en el ImageView

        float precioo=discoteca.getPrecio()*reserva.getCantEntradas();
        precio.setText(String.valueOf(precioo));

    }

    @FXML
    public void handleVerReservaButton() throws SQLException {
        // Suponiendo que tienes una reserva para pasar al método
         // Reemplaza esto con la reserva correcta
        verReservaCompra(reservita);
    }
    @FXML
    public void verReservaCompra(Reserva reserva) throws SQLException {


        System.out.println("HOLAAAAAA");
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Sesion sesion= Sesion.obtenerInstancia();
        Usuario usuario= logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
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
        // Obtener el dato BLOB de la base de datos
        byte[] qrCodeData = logicaDelNegocio.obtenerImagenQR(reserva.getIdEntrada());

        // Convertir el dato BLOB en una imagen y establecerla en el ImageView
        if (qrCodeData != null) {
            Image qrImage = new Image(new ByteArrayInputStream(qrCodeData));
            QR.setImage(qrImage);
        }
    }


    public void menuPrincipalButton(ActionEvent actionEvent) {
        Stage stage = new Stage();
        GestorDePantallas gestorDePantallas = GestorDePantallas.obtenerInstancia();
        gestorDePantallas.mostrarPantallaHome(stage);
    }
}

