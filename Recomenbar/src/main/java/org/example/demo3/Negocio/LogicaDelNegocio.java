package org.example.demo3.Negocio;
import org.example.demo3.Entidades.Discoteca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//prueba simon
public class LogicaDelNegocio {
    private static LogicaDelNegocio instancia;

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private LogicaDelNegocio() {
    }

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

        String sql = "INSERT INTO usuario VALUES(?,?,?,?,?)";
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
    public List<Discoteca> obtenerDiscotecas() throws SQLException {
        List<Discoteca> discotecas = new ArrayList<>();

        Connection conexion = ConexionBD.getConexion();

        String sql = "SELECT nombre, direccion, genero_musical, tipo, precio_entrada FROM discoteca";
        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Discoteca discoteca = new Discoteca();
            discoteca.setNombre(resultSet.getString("nombre"));
            discoteca.setUbicacion(resultSet.getString("direccion"));
            discoteca.setTipoMusica(resultSet.getString("genero_musical"));
            discoteca.setTipo(resultSet.getInt("tipo"));
            discoteca.setPrecioEntrada(resultSet.getInt("precio_entrada"));
            discotecas.add(discoteca);
        }

        resultSet.close();
        statement.close();
        conexion.close();

        return discotecas;
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
            discoteca.setPrecioEntrada(resultSet.getInt("precio_entrada"));
            discotecasFiltradas.add(discoteca);
        }
        resultSet.close();
        statement.close();
        conexion.close();
        return discotecasFiltradas;
    }

}
