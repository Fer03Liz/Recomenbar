package org.example.demo3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;
import org.example.demo3.Negocio.LogicaDelNegocio;
import org.example.demo3.Negocio.Sesion;
import org.example.demo3.Entidades.Entrada;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class ReservarController implements Initializable {

    @FXML
    private ListView<String> listViewBares;

    @FXML
    private TextField personasField;

    @FXML
    private TextField TextAux;

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
            listViewBares.getItems().addAll(discoteca.getNombre() + " en la direccion: " + discoteca.getUbicacion()
                    + " con un tipo de musica: " + discoteca.getTipoMusica());
        }
    }

    private List<Discoteca> obtenerBaresLocales() throws SQLException {
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        return logicaDelNegocio.disponibles();
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
        LocalDate fechaSeleccionada = fechaField.getValue();
        System.out.println(fechaSeleccionada);
        Usuario usuarioActual = Sesion.obtenerInstancia().getUsuarioActual();
        if (usuarioActual == null) {
            // Manejar el caso donde el usuario no está en sesión
            System.out.println("No hay usuario en sesión.");
            TextAux.setText("Debes iniciar sesión para reservar.");
            return;
        }
        int idUsuario = usuarioActual.getId();
        if (barSeleccionado != null) {
            if (fechaSeleccionada != null && (fechaSeleccionada.isAfter(LocalDate.now().minusDays(1))
                    && fechaSeleccionada.isBefore(LocalDate.now().plusDays(31)))) {
                try {
                    if (esNumerico(personasField.getText())) {
                        int cantidadPersonas = Integer.parseInt(personasField.getText());
                        if (cantidadPersonas >= 1 && cantidadPersonas <= 25) {
                            LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
                            // Convertir LocalDate a Timestamp
                            Timestamp fechaReserva = Timestamp.valueOf(fechaSeleccionada.atStartOfDay());
                            Sesion sesion = Sesion.obtenerInstancia();
                            Usuario usuario = logicaDelNegocio.UsuarioCorreo(sesion.getCorreo());
                            int idDiscoteca = obtenerIdDiscoteca(barSeleccionado);

                            if (logicaDelNegocio.registrarReserva(idUsuario, idDiscoteca, 0, idDiscoteca, fechaReserva,
                                    cantidadPersonas, true)) {
                                Entrada entrada = logicaDelNegocio.obtenerEntradaPorReserva(idUsuario, idDiscoteca,
                                        fechaReserva);
                                if (entrada != null) {
                                    // Generar el código QR después de registrar la reserva
                                    byte[] qrCode = entrada.generarQR(cantidadPersonas);
                                    if (qrCode != null) {
                                        entrada.setQr(qrCode);
                                        System.out.println("Reserva realizada con éxito y QR generado.");
                                        // Cierra la aplicación después de registrar la reserva y generar el QR
                                        Platform.exit();
                                    } else {
                                        System.out.println("Error al generar el código QR.");
                                    }
                                } else {
                                    System.out.println("Error al obtener la entrada para generar el QR.");
                                }
                            } else {
                                System.out.println("No se puede hacer la reserva");
                            }
                        } else {
                            personasField.clear();
                            personasField.setPromptText("El máximo para reservar es de 25 personas");
                        }
                    } else {
                        personasField.clear();
                        personasField.setPromptText("Digita un valor numérico");
                    }
                } catch (NumberFormatException e) {
                    personasField.clear();
                    personasField.setPromptText("Digita un valor numérico");
                    System.err.println("Error: La cantidad de personas debe ser un número entero válido.");
                }
            } else {
                TextAux.setPromptText("Escoja una fecha válida");
            }
        } else {
            TextAux.setText("SELECCIONA UN BAR");
        }
    }

    private int obtenerIdDiscoteca(String barSeleccionado) {
        // Asumiendo que el nombre de la discoteca está al principio de la cadena
        String nombreDiscoteca = barSeleccionado.split(" en la direccion: ")[0];
        LogicaDelNegocio logicaDelNegocio = LogicaDelNegocio.getInstancia();
        try {
            List<Discoteca> discotecas = logicaDelNegocio.disponibles();
            for (Discoteca discoteca : discotecas) {
                if (discoteca.getNombre().equals(nombreDiscoteca)) {
                    return discoteca.getId(); // Asumiendo que la clase Discoteca tiene un método getId()
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // En caso de que no se encuentre la discoteca
    }
}
