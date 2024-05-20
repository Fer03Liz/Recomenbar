package org.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Evento;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class VerInfoReservaController {

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

    public void setReserva(Reserva reserva) throws SQLException {
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
            javafx.scene.image.Image qrImage = new javafx.scene.image.Image(new ByteArrayInputStream(qrCodeData));
            QR.setImage(qrImage);
        }
    }

}
