package org.example.demo3.Negocio;

import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Evento;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;
import org.junit.jupiter.api.Test;

import java.sql.*;
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
        String correoNoExistente = "noexiste@.com";

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
        int edad = 10;
        String correo = "usuario_existente@example.com"; // Correo que ya existe en la base de datos
        String contraseña = "123";

        // Prueba el método registrarUsuario con un correo que ya existe en la base de datos
        try {
            boolean resultado = logica.registrarUsuario(nombre, edad, correo, contraseña);
            assertFalse(resultado, "No se esperaba que se registrara el usuario");
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
        String contraseñaIncorrecta = "12";

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

        // Datos para una reserva inválida (por ejemplo, IDs inexistentes o fuera de rango)
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
        int idDiscotecaInvalido = 10; // ID de discoteca inválido
        String nombreUsuario = "PontiFarra";
        float precio = 250000.00f;
        Timestamp date = Timestamp.valueOf("2024-06-01 20:00:00"); // Fecha y hora válidas

        // Prueba el método crearEventoPrivado con datos inválidos
        try {
            assertFalse(logica.crearEventoPrivado(idDiscotecaInvalido, nombreUsuario, precio, date), "Se esperaba que la creación del evento fallara");
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

        // Datos para una entrada con id_discoteca nulo
        Integer idDiscoteca = 50; // Aquí inicializamos idDiscoteca en null
        boolean vip = false;
        float precio = 20000.00f;

        // Prueba el método crearEntrada con un id_discoteca nulo
        try {
            assertFalse(logica.crearEntrada(idDiscoteca, vip, precio), "Se esperaba que la creación de la entrada fallara");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testDiscotecasDisponibles() {
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
    public void testEventosDisponibles() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();

        try {
            List<Evento> eventos = logica.EventosDisponibles();
            assertNotNull(eventos, "La lista de eventos no debería ser nula");
            assertFalse(eventos.isEmpty(), "La lista de eventos no debería estar vacía");

            // Verifica que se hayan cargado correctamente los nombres de los eventos
            for (Evento evento : eventos) {
                assertNotNull(evento.getNombre(), "El nombre del evento no debería ser nulo");
                assertFalse(evento.getNombre().isEmpty(), "El nombre del evento no debería estar vacío");
            }
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testUsuarioCorreoExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
        String correoExistente = "prueba@.com";

        try {
            Usuario usuario = logica.UsuarioCorreo(correoExistente);
            assertNotNull(usuario, "El usuario no debería ser nulo");
            assertEquals(correoExistente, usuario.getCorreo(), "El correo del usuario debería coincidir");

            // Verifica que los demás campos se hayan cargado correctamente
            assertNotNull(usuario.getNombre(), "El nombre del usuario no debería ser nulo");
            assertTrue(usuario.getEdad() > 0, "La edad del usuario debería ser mayor que 0");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testUsuarioCorreoFalla() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
        String correoInexistente = "hola@.com";

        try {
            Usuario usuario = logica.UsuarioCorreo(correoInexistente);
            assertNotNull(usuario, "El usuario no debería ser nulo");
            assertNull(usuario.getNombre(), "El nombre del usuario debería ser nulo cuando el correo no existe");
            assertEquals(0, usuario.getEdad(), "La edad del usuario debería ser 0 cuando el correo no existe");
            assertEquals(0, usuario.getId(), "El id del usuario debería ser 0 cuando el correo no existe");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testDiscotecaNombreExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
        String nombreExistente = "MonoBandido";

        try {
            Discoteca discoteca = logica.discotecaNombre(nombreExistente);
            assertNotNull(discoteca, "La discoteca no debería ser nula");
            assertEquals(nombreExistente, discoteca.getNombre(), "El nombre de la discoteca debería coincidir");

            // Verifica que los demás campos se hayan cargado correctamente
            assertNotNull(discoteca.getTipoMusica(), "El género musical de la discoteca no debería ser nulo");
            assertNotNull(discoteca.getUbicacion(), "La dirección de la discoteca no debería ser nula");
            assertTrue(discoteca.getTipo() >= 0, "El tipo de la discoteca debería ser un número no negativo");
            assertTrue(discoteca.getPrecio() >= 0, "El precio de entrada de la discoteca debería ser un número no negativo");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testDiscotecaNombreFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
        String nombreInexistente = "NombreInexistente";

        try {
            Discoteca discoteca = logica.discotecaNombre(nombreInexistente);
            assertNull(discoteca, "Se esperaba que no se recuperara ninguna discoteca con un nombre inexistente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testDiscotecaIDExitoso() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
        int idExistente = 1;

        try {
            Discoteca discoteca = logica.discotecaID(idExistente);
            assertNotNull(discoteca, "La discoteca no debería ser nula");
            assertEquals(idExistente, discoteca.getId(), "El ID de la discoteca debería coincidir");

            // Verifica que los demás campos se hayan cargado correctamente
            assertNotNull(discoteca.getNombre(), "El nombre de la discoteca no debería ser nulo");
            assertNotNull(discoteca.getTipoMusica(), "El género musical de la discoteca no debería ser nulo");
            assertNotNull(discoteca.getUbicacion(), "La dirección de la discoteca no debería ser nula");
            assertTrue(discoteca.getTipo() >= 0, "El tipo de la discoteca debería ser un número no negativo");
            assertTrue(discoteca.getPrecio() >= 0, "El precio de entrada de la discoteca debería ser un número no negativo");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    void testDiscotecaIDFallido() {
        LogicaDelNegocio logica = LogicaDelNegocio.getInstancia();
        int idInexistente = 1000; // Usamos un ID inexistente

        try {
            Discoteca discoteca = logica.discotecaID(idInexistente);

            // Verificar si la instancia de Discoteca es nula
            assertNull(discoteca, "No debería haber discoteca con un ID inexistente");
        } catch (SQLException e) {
            fail("Se produjo una excepción al ejecutar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void testIdEntradaExitoso() throws SQLException {
        // Llamar al método que se está probando
        LogicaDelNegocio logica = new LogicaDelNegocio();
        int id = logica.idEntrada(1); // Supongamos que queremos el ID de entrada para la discoteca con ID 1

        // Verificar si el resultado es el esperado
        assertEquals(1, id); // Asegúrate de ajustar el valor esperado según tu lógica
    }
    @Test
    public void testIdEntradaFallido() throws SQLException {
        // Llamar al método que se está probando
        LogicaDelNegocio logica = new LogicaDelNegocio();
        int id = logica.idEntrada(50); // Supongamos que queremos el ID de entrada para una discoteca inexistente

        // Verificar si el resultado es el esperado
        assertEquals(0, id); // Asegúrate de ajustar el valor esperado según tu lógica
    }
    @Test
    public void testReservaIdUsuarioExitoso() throws SQLException {
        LogicaDelNegocio logica = new LogicaDelNegocio();
        int idUsuario = 3; // ID de usuario existente en tu base de datos

        Reserva reserva = logica.reservaIdUsuario(idUsuario);

        assertNotNull(reserva, "La reserva no debe ser nula");
        assertEquals(idUsuario, reserva.getIdUsuario(), "El ID del usuario de la reserva debe ser el esperado");
        // Asegúrate de verificar otros campos importantes de la reserva aquí
        // Por ejemplo, asegúrate de que la reserva tenga una fecha válida y una cantidad de entradas válida
    }
    @Test
    public void testReservaIdUsuarioFallido() throws SQLException {
        // Llamar al método que se está probando con un ID de usuario inexistente
        LogicaDelNegocio logica = new LogicaDelNegocio();
        Reserva reserva = logica.reservaIdUsuario(99); // Supongamos que queremos la reserva de un usuario inexistente

        // Verificar si la reserva devuelta está vacía (es decir, no se encontró ninguna reserva)
        assertEquals(0, reserva.getId());
        assertFalse(reserva.getEstadoReserva());
    }
    @Test
    public void testEventoNombreExistente() {
        LogicaDelNegocio logicaDelNegocio = new LogicaDelNegocio();
        String nombreEventoExistente = "PontiFarra";

        try {
            Evento evento = logicaDelNegocio.eventoNombre(nombreEventoExistente);
            assertNotNull(evento);
            assertEquals(nombreEventoExistente, evento.getNombre());
            // Agrega más aserciones según sea necesario para verificar otros atributos del evento.
        } catch (SQLException e) {
            fail("Se lanzó una excepción: " + e.getMessage());
        }
    }
    @Test
    public void testEventoNombreInexistente() {
        LogicaDelNegocio logicaDelNegocio = new LogicaDelNegocio();
        String nombreEventoInexistente = "Nombre de Evento Inexistente";

        try {
            Evento evento = logicaDelNegocio.eventoNombre(nombreEventoInexistente);
            assertNull(evento);
        } catch (SQLException e) {
            fail("Se lanzó una excepción: " + e.getMessage());
        }
    }
    @Test
    public void testFiltrarDiscotecasExitoso() {
        LogicaDelNegocio negocio = new LogicaDelNegocio();

        // Prueba: Filtrar discotecas por tipo de música y dirección
        try {
            List<Discoteca> resultado = negocio.filtrarDiscotecas("Regueton", "Calle 85", "20000.00", "1");
            assertNotNull(resultado);
            assertTrue(resultado.size() > 0); // Asegurarse de que se encontraron discotecas
            // Aquí puedes agregar más aserciones según el resultado esperado
        } catch (SQLException e) {
            fail("Excepción SQL: " + e.getMessage());
        }
    }
    @Test
    public void testFiltrarDiscotecasFallido() {
        LogicaDelNegocio negocio = new LogicaDelNegocio();

        // Prueba: Intentar filtrar discotecas con parámetros nulos
        try {
            List<Discoteca> resultado = negocio.filtrarDiscotecas("Champeta", "Usaquen", "2000.00", "1");
            assertNotNull(resultado); // La lista no debe ser nula, pero estará vacía si no se encontraron resultados
            assertTrue(resultado.isEmpty()); // Asegurarse de que no se encontraron discotecas
        } catch (SQLException e) {
            fail("Excepción SQL: " + e.getMessage());
        }
    }

















}
