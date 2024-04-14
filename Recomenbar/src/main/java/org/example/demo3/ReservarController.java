package org.example.demo3;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ReservarController {

    public void insertarReserva()throws SQLException {
        String sql= "INSERT INTO city VALUES(?,?,?,?,?)";
        String bd="world";
        PreparedStatement sentencia=HelloApplication.ConectarBD(bd).prepareStatement(sql);
        System.out.println("Name: ");
        String name=HelloApplication.scanner.nextLine();
       // sentencia.setInt(1,);

    }
}
