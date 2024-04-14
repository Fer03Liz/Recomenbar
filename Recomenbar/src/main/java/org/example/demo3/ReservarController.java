package org.example.demo3;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ReservarController {

    public void insertarReserva()throws SQLException {
        String sql= "INSERT INTO city VALUES(?,?,?,?,?)";
        String bd="world";
        PreparedStatement sentencia=HelloApplication.ConectarBD(bd).prepareStatement(sql);
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
}
