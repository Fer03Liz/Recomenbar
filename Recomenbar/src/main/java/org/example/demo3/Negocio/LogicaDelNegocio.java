package org.example.demo3.Negocio;
import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.Entidades.Evento;
import org.example.demo3.Entidades.Reserva;
import org.example.demo3.Entidades.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//prueba simon
public class LogicaDelNegocio {
    private static LogicaDelNegocio instancia;

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private LogicaDelNegocio() {}
    // Método estático para obtener la instancia única de LogicaDelNegocio
    public static LogicaDelNegocio getInstancia() {
        if (instancia == null) {
            instancia = new LogicaDelNegocio();
        }
        return instancia;
    }

    public boolean usuarioExistente(String correo) throws SQLException {
        boolean existe = false;
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) AS total FROM usuario WHERE correo = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, correo);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                existe = total > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return existe;
    }

    public boolean registrarUsuario(String name, int edad, String correo, String password) throws SQLException {
        boolean insertado = false;
        Connection conexion = ConexionBD.getConexion();

        String sql = "INSERT INTO usuario (nombre, edad, correo, contraseña, tipo) VALUES(?,?,?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, name);
        sentencia.setInt(2, edad);
        sentencia.setString(3, correo);
        sentencia.setString(4, password);
        sentencia.setInt(5, 1);

        int filasINS = sentencia.executeUpdate();
        if (filasINS > 0) {
            insertado = true;
        }
        return insertado;
    }

    public boolean loginRealizado(String email, String password) {
        PreparedStatement sentencia = null;
        ResultSet resultados = null;
        boolean ingresado = false;
        try {
            Connection conexion = ConexionBD.getConexion();
            String sql = "SELECT * FROM usuario WHERE Correo = ? AND Contraseña = ?";
            sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, email);
            sentencia.setString(2, password);

            resultados = sentencia.executeQuery();

            if (resultados.next()) {
                ingresado = true;
                System.out.println("Login exitoso.");
            } else {
                System.out.println("Correo o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return ingresado;
    }

    public boolean registrarReserva(int idUsuario, int idDiscoteca, int idEntrada, int idEvento, Timestamp timestamp, int cantidadBoletas, boolean valida) throws SQLException {
        boolean reservaregistrada = false;
        Connection conexion = ConexionBD.getConexion();
        String sql = "INSERT INTO reserva (id_usuario, id_discoteca, id_entrada, id_evento, fecha, cantidad_boletas, valida) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(1, idUsuario);
        sentencia.setInt(2, idDiscoteca);
        sentencia.setInt(3, idEntrada);
        sentencia.setInt(4, idEvento);
        sentencia.setTimestamp(5, timestamp);
        sentencia.setInt(6, cantidadBoletas);
        sentencia.setBoolean(7, valida);

        int filasINS = sentencia.executeUpdate();
        if (filasINS > 0) {
            reservaregistrada = true;
            System.out.println("Reserva exitosa!!");
        } else {
            System.out.println("Algo salió mal...");
        }
        return reservaregistrada;
    }

    public boolean crearEventoPrivado(int id_discoteca, String nombreUsuario, float precio, Timestamp date) throws SQLException {
        boolean reservaregistrada = false;
        Connection conexion = ConexionBD.getConexion();
        String sql = "INSERT INTO evento (id_discoteca, nombre, precio, fecha, private) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, id_discoteca);
        sentencia.setString(2, nombreUsuario);
        sentencia.setFloat(3, precio);
        sentencia.setTimestamp(4, date);
        sentencia.setBoolean(5, true);
        int filasINS = sentencia.executeUpdate();
        if (filasINS > 0) {
            reservaregistrada = true;
            System.out.println("Evento creado!!");
        } else {
            System.out.println("Algo salió mal...");
        }
        return reservaregistrada;
    }

    public boolean crearEntrada(int id_discoteca, boolean vip, float precio) throws SQLException {
        boolean reservaregistrada = false;
        Connection conexion = ConexionBD.getConexion();
        String sql = "INSERT INTO entrada (id_discoteca, vip, precio) VALUES (?, ?, ?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, id_discoteca);
        sentencia.setBoolean(2, vip);
        sentencia.setFloat(3, precio);
        int filasINS = sentencia.executeUpdate();
        if (filasINS > 0) {
            reservaregistrada = true;
            System.out.println("Entrada creada!!");
        } else {
            System.out.println("Algo salió mal...");
        }
        return reservaregistrada;
    }

    public List<Discoteca> disponibles() throws SQLException {
        List<Discoteca> discotecas = new ArrayList<>();

        // Obtén la conexión a través del Singleton
        Connection conexion = ConexionBD.getConexion();

        // Preparar la sentencia SQL
        String sql = "SELECT nombre, direccion, genero_Musical FROM discoteca";
        PreparedStatement statement = conexion.prepareStatement(sql);

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Recorrer los resultados y agregar los nombres a la lista
        while (resultSet.next()) {
            Discoteca discoteca = new Discoteca();
            discoteca.setNombre( resultSet.getString("Nombre"));
            discoteca.setUbicacion(resultSet.getString("Direccion"));
            discoteca.setTipoMusica(resultSet.getString("genero_Musical"));
            discotecas.add(discoteca);
        }

        // Cerrar la conexión y liberar los recursos
        resultSet.close();
        statement.close();
        conexion.close();

        return discotecas;
    }

    public List<Evento> EventosDisponibles() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        // Obtén la conexión a través del Singleton
        Connection conexion = ConexionBD.getConexion();

        // Preparar la sentencia SQL
        String sql = "SELECT nombre, precio, fecha FROM evento WHERE private = false ORDER BY fecha DESC";
        PreparedStatement statement = conexion.prepareStatement(sql);

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Recorrer los resultados y agregar los nombres a la lista
        while (resultSet.next()) {
            Evento  evento = new Evento();
            evento.setNombre( resultSet.getString("Nombre"));
            evento.setPrecio(resultSet.getFloat("precio"));
            evento.setFecha(resultSet.getDate("fecha"));
            eventos.add(evento);
        }

        // Cerrar la conexión y liberar los recursos
        resultSet.close();
        statement.close();
        conexion.close();

        return eventos;

    }

    public Usuario UsuarioCorreo(String correo) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        Usuario usuario=new Usuario();
        String sql = "SELECT id, nombre, edad FROM usuario WHERE correo = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, correo);
        ResultSet resultSet = statement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            usuario.setId(resultSet.getInt("id"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setEdad(resultSet.getInt("edad"));
            usuario.setCorreo(correo);
        }
        return usuario;
    }

    public Discoteca discotecaNombre(String nombre) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        Discoteca discoteca = new Discoteca();
        String sql = "SELECT id, direccion, genero_musical, tipo, precio_entrada FROM discoteca WHERE nombre = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, nombre);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            discoteca.setId(resultSet.getInt("id"));
            discoteca.setNombre(nombre);
            discoteca.setTipoMusica(resultSet.getString("genero_musical"));
            discoteca.setUbicacion(resultSet.getString("direccion"));
            discoteca.setTipo(resultSet.getInt("tipo"));
            discoteca.setPrecio(resultSet.getFloat("precio_entrada"));
        }

        return discoteca;
    }

    public Discoteca discotecaID(int id) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        Discoteca discoteca = new Discoteca();
        String sql = "SELECT nombre, direccion, genero_musical, tipo, precio_entrada FROM discoteca WHERE id = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            discoteca.setId(id);
            discoteca.setNombre(resultSet.getString("nombre"));
            discoteca.setTipoMusica(resultSet.getString("genero_musical"));
            discoteca.setUbicacion(resultSet.getString("direccion"));
            discoteca.setTipo(resultSet.getInt("tipo"));
            discoteca.setPrecio(resultSet.getFloat("precio_entrada"));
        }

        return discoteca;
    }

    public int idEntrada(int idDiscoteca) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        String sql = "SELECT id FROM entrada WHERE id_discoteca = ? ";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, idDiscoteca);
        ResultSet resultSet = statement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }

    public Reserva reservaIdusario (int id_usuario) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        String sql = "SELECT id, id_discoteca, id_entrada, id_evento, fecha, cantidad_boletas, valida FROM usuario WHERE id_usuario = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, id_usuario);
        ResultSet resultSet = statement.executeQuery();
        Reserva reserva= new Reserva();
        if (resultSet.next()) {
            reserva.setId(resultSet.getInt("id"));
            reserva.setIdUsuario(id_usuario);
            reserva.setIdDiscoteca(resultSet.getInt("id_discoteca"));
            reserva.setIdEntrada(resultSet.getInt("id_entrada"));
            reserva.setIdEvento(resultSet.getInt("id_evento"));
            reserva.setFecha(resultSet.getDate("fecha"));
            reserva.setCantEntradas(resultSet.getInt("cantidad_boletas"));
            reserva.setEstadoReserva(resultSet.getBoolean("valida"));
        }
        return reserva;
    }

    public Evento eventoNombre(String nombre) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        String sql = "SELECT id, id_discoteca, precio, fecha, private FROM evento WHERE nombre = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, nombre);
        ResultSet resultSet = statement.executeQuery();
        Evento evento= new Evento();
        if (resultSet.next()) {
            evento.setId(resultSet.getInt("id"));
            evento.setId_discoteca(resultSet.getInt("id_discoteca"));
            evento.setNombre(nombre);
            evento.setPrecio(resultSet.getFloat("precio"));
            evento.setFecha(resultSet.getDate("fecha"));
            evento.setPrivado(resultSet.getBoolean("private"));
        }
        return evento;
    }

    public List<Discoteca> filtrarDiscotecas(String tipoMusica, String direccion, String presupuesto, String experiencia) throws SQLException {
        List<Discoteca> discotecasFiltradas = new ArrayList<>();
        Connection conexion = ConexionBD.getConexion();
        StringBuilder sqlBuilder = new StringBuilder("SELECT nombre, direccion, genero_musical, tipo, precio_entrada FROM discoteca WHERE 1=1");
        if (tipoMusica != null && !tipoMusica.isEmpty()) {
            sqlBuilder.append(" AND genero_musical = ?");
        }
        if (direccion != null && !direccion.isEmpty()) {
            sqlBuilder.append(" AND direccion = ?");
        }
        if (presupuesto != null && !presupuesto.isEmpty()) {
            String[] rango = presupuesto.split("-");
            if (rango.length == 2) {
                sqlBuilder.append(" AND (precio_entrada >= ? AND precio_entrada <= ? OR precio_entrada < ?)");
            } else {
                sqlBuilder.append(" AND (precio_entrada = ? OR precio_entrada < ?)");
            }
        }
        PreparedStatement statement = conexion.prepareStatement(sqlBuilder.toString());
        int index = 1;
        if (tipoMusica != null && !tipoMusica.isEmpty()) {
            statement.setString(index++, tipoMusica);
        }
        if (direccion != null && !direccion.isEmpty()) {
            statement.setString(index++, direccion);
        }
        if (presupuesto != null && !presupuesto.isEmpty()) {
            String[] rango = presupuesto.split("-");
            if (rango.length == 2) {
                int precioMin = Integer.parseInt(rango[0].replace(".", ""));
                int precioMax = Integer.parseInt(rango[1].replace(".", ""));
                statement.setInt(index++, precioMin);
                statement.setInt(index++, precioMax);
                statement.setInt(index++, precioMax); // Añadir el valor de presupuesto nuevamente para el caso de < presupuesto
            } else {
                int precio = Integer.parseInt(presupuesto.replace(".", ""));
                statement.setInt(index++, precio);
                statement.setInt(index++, precio); // Añadir el valor de presupuesto nuevamente para el caso de < presupuesto
            }
        }
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Discoteca discoteca = new Discoteca();
            discoteca.setNombre(resultSet.getString("nombre"));
            discoteca.setUbicacion(resultSet.getString("direccion"));
            discoteca.setTipoMusica(resultSet.getString("genero_musical"));
            discoteca.setTipo(resultSet.getInt("tipo"));
            discoteca.setPrecio(resultSet.getInt("precio_entrada"));
            discotecasFiltradas.add(discoteca);
        }
        resultSet.close();
        statement.close();
        conexion.close();
        return discotecasFiltradas;
    }
}
