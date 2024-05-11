package org.example.demo3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;

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
        List<Discoteca> discotecas = null;
        try {
            discotecas = obtenerBaresLocales();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Llenar la ListView con los nombres de los bares
        for (Discoteca discoteca : discotecas) {
            listViewBares.getItems().addAll(discoteca.getNombre()+" en la direccion: "+discoteca.getUbicacion()+" con un tipo de musica: "+discoteca.getTipoMusica());
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

    @FXML
    private void onReservarButtonClick() throws SQLException {
        // Obtener la selección del usuario del ListView
        String barSeleccionado = listViewBares.getSelectionModel().getSelectedItem();
        // Discoteca d = new Discoteca();
        LocalDate fechaSeleccionada = fechaField.getValue();
        System.out.println(fechaSeleccionada);
        if(barSeleccionado != null){
            if(fechaSeleccionada != null && (fechaSeleccionada.isAfter(LocalDate.now().minusDays(1)) && fechaSeleccionada.isBefore(LocalDate.now().plusDays(31)))){
                try {
                    if (esNumerico(personasField.getText())) {
                        int cantidadPersonas = Integer.parseInt(personasField.getText());
                        if(cantidadPersonas >=1 && cantidadPersonas<=25) {
                            LogicaDelNegocio logicaDelNegocio= LogicaDelNegocio.getInstancia();
                            // Convertir LocalDate a Timestamp
                            Timestamp timestamp = Timestamp.valueOf(fechaSeleccionada.atStartOfDay());
                            Sesion sesion= Sesion.obtenerInstancia();
                            int idUsuario= 1;
                            int idDiscoteca= 1;
                            int idEntrada= 1;
                            int idEvento= 1;
                            if(logicaDelNegocio.registrarReserva(idUsuario,idDiscoteca,idEntrada,idEvento,timestamp,cantidadPersonas,true)){
                                // Cierra la aplicación después de registrar la reserva correctamente
                                Platform.exit();
                            }else{
                                System.out.printf("No se puede hacer la reserva");
                            }
                        }else{
                            personasField.clear();
                            personasField.setPromptText("El maximo para reservar es de 25 personas");
                        }
                    }else{
                        personasField.clear();
                        personasField.setPromptText("Digita un valor numerico");
                    }
                } catch (NumberFormatException e) {
                    personasField.clear();
                    personasField.setPromptText("Digita un valor numerico");
                    System.err.println("Error: La cantidad de personas debe ser un número entero válido.");
                }
            }else{
                TextAux.setPromptText("Escoja una fecha valida");
            }
        }else{
            TextAux.setText("SELECCIONA UN BAR");
        }
    }
}
