package org.example.demo3;

import com.google.zxing.WriterException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.demo3.Entidades.*;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.List;


public class ReservarDiscotecaController {

    @FXML
    private ListView<String> listViewBares;

    @FXML
    private CheckBox vip;
    @FXML
    private TextField personasField;

    @FXML
    private Label TextAux1;
    @FXML
    private Label  TextAux2;
    @FXML
    private Label  TextAux3;

    @FXML
    private DatePicker fechaField;

    // Método para inicializar la lista de discotecas
    public void setDiscotecas(List<Discoteca> discotecas) {
        for (Discoteca discoteca : discotecas) {
            listViewBares.getItems().add(discoteca.getNombre());
        }
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
        String nombre = listViewBares.getSelectionModel().getSelectedItem();
        System.out.println(nombre);
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Seleccione una Discoteca");
            TextAux1.setText("Seleccione una Discoteca");
            validado = false;
        }
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        Discoteca discoteca=logicaDelNegocio.discotecaNombre(nombre);
        if(discoteca.getId()==0){
            System.out.println("No se ha encontrado la discoteca");
            TextAux1.setText("No se ha encontrado la discoteca");
            validado = false;
        }
        if(!esNumerico(personasField.getText())){
            System.out.println("No es numerico el dato");
            TextAux2.setText("No es numerico el dato");
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


    public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el valor máximo.");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    @FXML
    private void onReservarButtonClick(ActionEvent event) throws SQLException {
        TextAux1.setText("");
        TextAux2.setText("");
        TextAux3.setText("");
        // Obtener la selección del usuario del ListView
        String barSeleccionado = listViewBares.getSelectionModel().getSelectedItem();
        LocalDate fechaSeleccionada = fechaField.getValue();
        System.out.println(fechaSeleccionada);
        if(validarFormulario()){
            try {
                LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
                int cantidadPersonas = Integer.parseInt(personasField.getText());
                Timestamp timestamp = Timestamp.valueOf(fechaSeleccionada.atStartOfDay());
                boolean esVip= vip.isSelected();
                System.out.println(vip.isSelected());
                System.out.println("VARIABLE "+vip);
                Sesion sesion = Sesion.obtenerInstancia();
                Usuario usuario = logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
                Discoteca discoteca= logicaDelNegocio.discotecaNombre(barSeleccionado);
                //Instanciar
                String nombreEvento= usuario.getNombre()+"Privado";
                logicaDelNegocio.crearEventoPrivado(discoteca.getId(), nombreEvento, discoteca.getPrecio(), timestamp);
                Evento evento = logicaDelNegocio.eventoNombre(nombreEvento);
                int idR= generateRandomNumber(1,100000);
                logicaDelNegocio.crearEntrada(idR,discoteca.getId(),esVip,evento.getPrecio(),timestamp,discoteca.getNombre(),cantidadPersonas);
                Entrada entrada= logicaDelNegocio.entradaIDR(idR);
                if (logicaDelNegocio.registrarReserva(usuario.getId(), discoteca.getId(), entrada.getId(), evento.getId(), timestamp,cantidadPersonas,true)) {
                    System.out.printf("Se puede hacer la reserva");
                    System.out.print("inf reserva antes :"+idR+"  "+usuario.getId()+"    "+ discoteca.getId());
                    GestorDePantallas gestorDePantallas= GestorDePantallas.obtenerInstancia();
                    Reserva reservita=new Reserva(idR, usuario.getId(), discoteca.getId(), entrada.getId(), evento.getId(),timestamp,cantidadPersonas,true);
                    System.out.print("inf reserva despues:"+reservita.getId()+"  "+reservita.getIdUsuario()+"    "+reservita.getIdDiscoteca());
                   gestorDePantallas.mosrtarPantallaCompra(event,reservita);
                } else {
                    System.out.printf("No se puede hacer la reserva");
                }
            }catch (NumberFormatException e) {
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
