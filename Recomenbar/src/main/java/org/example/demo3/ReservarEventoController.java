package org.example.demo3;

import com.google.zxing.WriterException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Evento;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.List;

public class ReservarEventoController implements Initializable {

    @FXML
    private ListView<String> listViewEventos;

    @FXML
    private TextField personasField;

    @FXML
    private Label  TextAux1;
    @FXML
    private Label  TextAux2;
    @FXML
    private Label  TextAux3;
    @FXML
    private CheckBox vip;

    @FXML
    private DatePicker fechaField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar la lista de nombres de bares
        List<Evento> eventos = null;
        try {
            eventos = obtenerEventosPublicos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Llenar la ListView con los nombres de los bares
        for (Evento evento : eventos) {
            listViewEventos.getItems().addAll(evento.getNombre());
        }
    }

    private List<Evento> obtenerEventosPublicos() throws SQLException {
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        List<Evento> eventos = logicaDelNegocio.EventosDisponibles();//Instanciar bares
        // Agregar más nombres de bares según sea necesario
        return eventos;
    }

    public static boolean esNumerico(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean validarFormulario() throws SQLException {
        boolean validado = true;
        LocalDate fechaSeleccionada = fechaField.getValue();
        String nombre = listViewEventos.getSelectionModel().getSelectedItem();
        System.out.println(nombre);
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Seleccione un Evento");
            TextAux1.setText("Seleccione un Evento");
            validado = false;
        }
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Evento evento=logicaDelNegocio.eventoNombre(nombre);
        if(evento.getId()==0){
            System.out.println("No se ha encontrado la discoteca");
            TextAux1.setText("No se ha encontrado la discoteca");
            validado = false;
        }
        if(!esNumerico(personasField.getText())){
            System.out.println("No es numerico el dato");
            TextAux2.setText("Pon un numero en el campo anterior");
            validado = false;
        }
        int cantidadPersonas = Integer.parseInt(personasField.getText());
        if(cantidadPersonas<=0){
            System.out.println("Debe haber al menos una persona en la reserva");
            TextAux2.setText("Debe haber al menos una persona en la reserva");
            validado = false;
        }
        if(cantidadPersonas>20){
            System.out.println("El maximo de personas por reserva es 20");
            TextAux2.setText("El maximo de personas por reserva es 20");
            validado = false;
        }
        if(fechaSeleccionada == null){
            System.out.println("Seleccione una fecha");
            TextAux3.setText("Seleccione una fecha");
            validado = false;
        }
        if(!fechaSeleccionada.isAfter(LocalDate.now().minusDays(1))){
            System.out.println("La fecha es ya ocurrio");
            TextAux3.setText("La fecha es ya ocurrio");
            validado = false;
        }
        if(!fechaSeleccionada.isBefore(LocalDate.now().plusDays(31))){
            System.out.println("La fecha es demasiado lejana");
            TextAux3.setText("La fecha es demasiado lejana");
            validado = false;
        }
        return validado;
    }

    @FXML
    private void onReservarButtonClick() throws SQLException, IOException, WriterException {
        // Obtener la selección del usuario del ListView
        String eventoSeleccionado = listViewEventos.getSelectionModel().getSelectedItem();
        LocalDate fechaSeleccionada = fechaField.getValue();
        boolean esVip= vip.isSelected();
        if(validarFormulario()){
            LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
            // Convertir LocalDate a Timestamp
            Timestamp timestamp = Timestamp.valueOf(fechaSeleccionada.atStartOfDay());
            Sesion sesion= Sesion.obtenerInstancia();
            int cantidadPersonas = Integer.parseInt(personasField.getText());
            Evento evento=logicaDelNegocio.eventoNombre(eventoSeleccionado);
            Usuario usuario= logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
            Discoteca discoteca= logicaDelNegocio.discotecaID(evento.getId_discoteca());
            logicaDelNegocio.crearEntrada(discoteca.getId(),esVip,evento.getPrecio());
            int entradaID= logicaDelNegocio.idEntrada(discoteca.getId());
            if(logicaDelNegocio.registrarReserva(usuario.getId(),discoteca.getId(),entradaID,evento.getId(),timestamp,cantidadPersonas,true)){
                // Cierra la aplicación después de registrar la reserva correctamente
                Platform.exit();
            }else{
                System.out.printf("No se puede hacer la reserva");
            }
        }
    }
}
