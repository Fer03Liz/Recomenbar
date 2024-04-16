package org.example.demo3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Scanner;


public class HelloApplication extends Application {
//PreparedStatement ps;
    public static Scanner scanner=new Scanner(System.in);
    @Override
    public void start(Stage primaryStage) {
        // Instancia el gestor de pantallas y muestra la pantalla inicial
        GestorDePantallas gestorDePantallas = new GestorDePantallas(primaryStage);
        gestorDePantallas.mostrarPantalla("Home");
    }


    public static void main(String[] args) throws SQLException {

        Connection connection= ConectarBD("recomenbar");// nombre de la base de datos a la cual será conectada
        launch(args);
        connection.close();
    }

    public static Connection ConectarBD(String bd ) throws SQLException {//el string es para saber el nombre de la base de datos
        // siempre y cuando esté dentro del mismo host y tenga los permisos (usuario y contraseña)
        Connection conexion;
        String host="jdbc:mysql://localhost/";
        String user="root";//usuario de la base de datos (por defecto es root)
        String pass= "Tinto56Ñ*52#";//contraseña de la base de datos
        System.out.println("Conectando...");
        try {
            conexion= DriverManager.getConnection(host+bd,user,pass);
            if (conexion!=null){
                System.out.println("Coneccion exitosa!!");
                System.out.println("-------------------");
            }


        }catch (SQLException e){

            System.out.println("Error: "+e.getErrorCode()+" "+e.getMessage());
            System.out.println(e.getMessage());

            //statement.close();
            //resultSet.close();

            throw new RuntimeException(e);

        }

        return conexion;

    }
}
