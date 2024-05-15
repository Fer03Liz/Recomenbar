package org.example.demo3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;
import org.w3c.dom.Text;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.List;


public class ReservarDiscotecaController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar la lista de nombres de bares
        List<Discoteca> discotecas = null;
        try {
            discotecas = obtenerBaresLocales();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Llenar la ListView con los nombres de los bares
        for (Discoteca discoteca : discotecas) {
            listViewBares.getItems().addAll(discoteca.getNombre());
        }
    }

    private List<Discoteca> obtenerBaresLocales() throws SQLException {
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        List<Discoteca> discotecas = logicaDelNegocio.disponibles();//Instanciar bares
        // Agregar más nombres de bares según sea necesario
        return discotecas;
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
        Discoteca discoteca=logicaDelNegocio.DiscotecaNombre(nombre);
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

    @FXML
    private void onReservarButtonClick() throws SQLException {
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
                Discoteca discoteca= logicaDelNegocio.DiscotecaNombre(barSeleccionado);
                //Instanciar
                logicaDelNegocio.crearEntrada(discoteca.getId(),esVip,discoteca.getPrecio());
                int idEntrada = logicaDelNegocio.idEntrada(discoteca.getId());
                //System.out.println("ENTRADA ID="+idEntrada);
                String nombreEvento= usuario.getNombre()+"Privado";
                logicaDelNegocio.crearEventoPrivado(discoteca.getId(), nombreEvento, discoteca.getPrecio(), timestamp);
                int idEvento = logicaDelNegocio.idEvento(nombreEvento);
                if (logicaDelNegocio.registrarReserva(usuario.getId(), discoteca.getId(), idEntrada,idEvento,timestamp,cantidadPersonas,true)) {
                    // Cierra la aplicación después de registrar la reserva correctamente
                    Platform.exit();
                } else {
                    System.out.printf("No se puede hacer la reserva");
                }
            }catch (NumberFormatException e) {
                personasField.clear();
                personasField.setPromptText("Digita un valor numerico");
                System.err.println("Error: La cantidad de personas debe ser un número entero válido.");
            }
        }
    }

}