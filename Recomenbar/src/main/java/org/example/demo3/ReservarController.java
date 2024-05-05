package org.example.demo3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Negocio.LogicaDelNegocio;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

public class ReservarController implements Initializable {

    @FXML
    private ListView<String> listViewBares;

    @FXML
    private TextField personasField;

    @FXML
    private TextField  TextAux;

    @FXML
    private DatePicker fechaField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar la lista de nombres de bares
        List<String> nombresBares = null;
        try {
            nombresBares = obtenerNombresBaresLocales();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Llenar la ListView con los nombres de los bares
        listViewBares.getItems().addAll(nombresBares);
    }

    private List<String> obtenerNombresBaresLocales() throws SQLException {
        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
        List<String> nombresBares = logicaDelNegocio.disponibles();//Instanciar bares
        // Agregar más nombres de bares según sea necesario
        return nombresBares;
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

    @FXML
    private void onReservarButtonClick() throws SQLException {
        // Obtener la selección del usuario del ListView
        String barSeleccionado = listViewBares.getSelectionModel().getSelectedItem();
        // Discoteca d = new Discoteca();
        LocalDate fechaSeleccionada = fechaField.getValue();
        System.out.println(fechaSeleccionada);
        if(barSeleccionado != null){
            if(fechaSeleccionada != null && (fechaSeleccionada.isAfter(LocalDate.now()) || fechaSeleccionada.isEqual(LocalDate.now()))){
                if (esNumerico(personasField.getText())) {
                    int cantidadPersonas = Integer.parseInt(personasField.getText());
                    if(cantidadPersonas >=1) {
                        LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
                        // Convertir LocalDate a Timestamp
                        Timestamp timestamp = Timestamp.valueOf(fechaSeleccionada.atStartOfDay());
                        if(logicaDelNegocio.registrarReserva(cantidadPersonas, timestamp, barSeleccionado)){
                            // Cierra la aplicación después de registrar la reserva correctamente
                            Platform.exit();
                        }else{
                            System.out.printf("No se puede hacer la reserva");
                        }
                    }
                }else{
                    personasField.clear();
                    personasField.setPromptText("Digita un valor numerico");
                }
            }else{
                fechaField.setPromptText("Escoja una fecha valida");
            }
        }else{
            TextAux.setText("SELECCIONA UN BAR");
        }
    }
}
