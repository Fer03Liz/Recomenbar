package org.example.demo3.Negocio;

import org.example.demo3.Entidades.Discoteca;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogicaDelNegocioTest {

    @Test
    public void testUsuarioExistente() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Correo de usuario existente en la base de datos
        String correoExistente = "prueba@.com";

        // Prueba el método usuarioExistente para un usuario que debería existir en la base de datos
        try {
            assertTrue(logica.usuarioExistente(correoExistente), "El usuario debería existir en la base de datos");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testUsuarioNoExistente() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Correo de usuario que no existe en la base de datos
        String correoNoExistente = "prueba@.com";

        // Prueba el método usuarioExistente para un usuario que no debería existir en la base de datos
        try {
            assertFalse(logica.usuarioExistente(correoNoExistente), "El usuario no debería existir en la base de datos");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }

        // Imprime un mensaje para indicar que la prueba pasó correctamente
        System.out.println("La prueba de usuarioNoExistente pasó correctamente.");
    }

    @Test
    public void testRegistrarUsuarioExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos del nuevo usuario
        String nombre = "Nuevo Usuario";
        int edad = 30;
        String correo = "nuevo_usuario@example.com";
        String contraseña = "contraseña123";

        // Prueba el método registrarUsuario para insertar un nuevo usuario en la base de datos
        try {
            assertTrue(logica.registrarUsuario(nombre, edad, correo, contraseña), "Se esperaba que el usuario se registrara correctamente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }

        // Verifica si el usuario realmente se insertó en la base de datos
        try {
            assertTrue(logica.usuarioExistente(correo), "El usuario debería existir en la base de datos después de ser registrado");
        } catch (SQLException e) {
            fail("Se produjo una excepción al verificar si el usuario existe en la base de datos: " + e.getMessage());
        }
    }

    @Test
    public void testRegistrarUsuarioFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos del usuario que ya está registrado
        String nombre = "Prueba";
        int edad = 20;
        String correo = "prueba@.com"; // Usuario que ya existe en la base de datos
        String contraseña = "123";

        // Prueba el método registrarUsuario con un correo que ya existe en la base de datos
        try {
            assertFalse(logica.registrarUsuario(nombre, edad, correo, contraseña), "No se esperaba que se registrara el usuario");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testLoginExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos de usuario existente
        String correoExistente = "prueba@.com";
        String contraseñaCorrecta = "123";

        // Prueba el método loginRealizado con una combinación de correo y contraseña válidos
        assertTrue(logica.loginRealizado(correoExistente, contraseñaCorrecta), "Se esperaba un inicio de sesión exitoso");
    }
    @Test
    public void testLoginFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos de usuario existente
        String correoExistente = "prueba@.com";
        String contraseñaIncorrecta = "123";

        // Prueba el método loginRealizado con una contraseña incorrecta
        assertFalse(logica.loginRealizado(correoExistente, contraseñaIncorrecta), "Se esperaba un inicio de sesión fallido");
    }
    @Test
    public void testRegistrarReservaExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para la reserva
        int idUsuario = 1;
        int idDiscoteca = 1;
        int idEntrada = 1;
        int idEvento = 1;
        Timestamp timestamp = Timestamp.valueOf("2024-06-08 00:00:00");
        int cantidadBoletas = 2;
        boolean valida = true;

        // Prueba el método registrarReserva para insertar una reserva en la base de datos
        try {
            assertTrue(logica.registrarReserva(idUsuario, idDiscoteca, idEntrada, idEvento, timestamp, cantidadBoletas, valida), "Se esperaba que la reserva se registrara correctamente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testRegistrarReservaFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para una reserva inválida (por ejemplo, IDs inexistentes)
        int idUsuario = -1; // ID de usuario inválido
        int idDiscoteca = -1; // ID de discoteca inválido
        int idEntrada = -1; // ID de entrada inválido
        int idEvento = -1; // ID de evento inválido
        Timestamp timestamp = Timestamp.valueOf("2024-06-08 00:00:00"); // Fecha y hora válidas
        int cantidadBoletas = 2;
        boolean valida = true;

        // Prueba el método registrarReserva con datos inválidos
        try {
            assertFalse(logica.registrarReserva(idUsuario, idDiscoteca, idEntrada, idEvento, timestamp, cantidadBoletas, valida), "Se esperaba que la reserva fallara");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testCrearEventoPrivadoExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para el evento privado
        int idDiscoteca = 2;  // ID de discoteca existente
        String nombreUsuario = "PontiFarra";
        float precio = 250000.00f;
        Timestamp date = Timestamp.valueOf("2024-06-01 20:00:00"); // Fecha y hora válidas

        // Prueba el método crearEventoPrivado para insertar un evento en la base de datos
        try {
            assertTrue(logica.crearEventoPrivado(idDiscoteca, nombreUsuario, precio, date), "Se esperaba que el evento se creara correctamente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testCrearEventoPrivadoFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para un evento inválido (por ejemplo, discoteca inexistente)
        int idDiscoteca = -1; // ID de discoteca inválido
        String nombreUsuario = "PontiFarra";
        float precio = 250000.00f;
        Timestamp date = Timestamp.valueOf("2024-06-01 20:00:00"); // Fecha y hora válidas

        // Prueba el método crearEventoPrivado con datos inválidos
        try {
            assertFalse(logica.crearEventoPrivado(idDiscoteca, nombreUsuario, precio, date), "Se esperaba que la creación del evento fallara");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testCrearEntradaVIPExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para la entrada VIP
        int idDiscoteca = 1;  // ID de discoteca existente
        boolean vip = true;
        float precio = 50000.00f;

        // Prueba el método crearEntrada para insertar una entrada VIP en la base de datos
        try {
            assertTrue(logica.crearEntrada(idDiscoteca, vip, precio), "Se esperaba que la entrada VIP se creara correctamente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testCrearEntradaNormalExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para la entrada normal
        int idDiscoteca = 1;  // ID de discoteca existente
        boolean vip = false;
        float precio = 20000.00f;

        // Prueba el método crearEntrada para insertar una entrada normal en la base de datos
        try {
            assertTrue(logica.crearEntrada(idDiscoteca, vip, precio), "Se esperaba que la entrada normal se creara correctamente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testCrearEntradaFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Datos para una entrada inválida (por ejemplo, discoteca inexistente)
        int idDiscoteca = -1; // ID de discoteca inválido
        boolean vip = false;
        float precio = 20000.00f;

        // Prueba el método crearEntrada con datos inválidos
        try {
            assertFalse(logica.crearEntrada(idDiscoteca, vip, precio), "Se esperaba que la creación de la entrada fallara");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testDiscotecasDisponiblesExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        try {
            List<Discoteca> discotecas = logica.disponibles();
            assertNotNull(discotecas, "La lista de discotecas no debería ser nula");
            assertFalse(discotecas.isEmpty(), "La lista de discotecas no debería estar vacía");

            // Verifica que se hayan cargado correctamente los nombres de las discotecas
            for (Discoteca discoteca : discotecas) {
                assertNotNull(discoteca.getNombre(), "El nombre de la discoteca no debería ser nulo");
                assertFalse(discoteca.getNombre().isEmpty(), "El nombre de la discoteca no debería estar vacío");
            }
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testDiscotecasDisponiblesError() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        // Simulamos un error de SQL al obtener las discotecas
        try {
            List<Discoteca> discotecas = logica.disponibles();
            fail("Se esperaba una SQLException");
        } catch (SQLException e) {
            assertNotNull(e.getMessage(), "Se debería proporcionar un mensaje de error");
        }
    }






}
