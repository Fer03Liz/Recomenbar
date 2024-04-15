package org.example.demo3.Negocio;

import org.example.demo3.Entidades.Discoteca;
import org.example.demo3.HelloApplication;
import org.example.demo3.ReservarController;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class LogicaDelNegocio {


        public static void registrarUsuario() throws SQLException{

            Connection conexion = HelloApplication.ConectarBD("recomenbar");

            String sql= "INSERT INTO registrarusuario VALUES(?,?,?,?,?)";
            String bd="recomenbar";
            PreparedStatement sentencia= HelloApplication.ConectarBD(bd).prepareStatement(sql);
/*
        System.out.println("ID: ");
        int ID =HelloApplication.scanner.nextInt();
        sentencia.setInt(1, ID);
        HelloApplication.scanner.nextLine();
*/
            //
            System.out.println("Name: ");
            String name=HelloApplication.scanner.nextLine();
            sentencia.setString(2,name);
            //
            System.out.println("Correo: ");
            String email=HelloApplication.scanner.nextLine();
            sentencia.setString(3,email);
            //
            System.out.println("Edd: ");
            int Edd =HelloApplication.scanner.nextInt();
            sentencia.setInt(4, Edd);
            HelloApplication.scanner.nextLine();
            //
            System.out.println("Contraseña: ");
            String password=HelloApplication.scanner.nextLine();
            sentencia.setString(5,password);

            int filasINS=sentencia.executeUpdate();//filas insertadas, da la info de filas modificadas o insertadas
            if (filasINS>0){
                System.out.println("Insertado con exito!!");
            }
}


    public boolean loginRealizado(){
        boolean resultado = false;
        return resultado;
    }

    public void registrarReserva() throws SQLException {
        String sql= "INSERT INTO registroreservas VALUES(?,?)";
        String bd="world";
        PreparedStatement sentencia= HelloApplication.ConectarBD(bd).prepareStatement(sql);

        System.out.println("Cantidad de personas: ");
        int cantPersonas=HelloApplication.scanner.nextInt();
        sentencia.setInt(1,cantPersonas);
        //
        System.out.println("Fecha de la reserva: ");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sentencia.setTimestamp(2, timestamp);


        int filasINS=sentencia.executeUpdate();//filas insertadas, da la info de filas modificadas o insertadas
        if (filasINS>0){
            System.out.println("Reserva exitosa!!");
        }
        else {
            System.out.println("Algo salio mal...");
        }

    }

    public List<Discoteca> disponibles(){
        List<Discoteca> disponibles = new ArrayList<Discoteca>();
        return disponibles;
    }
}
