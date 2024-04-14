package org.example.demo3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.PrintStream;
import java.sql.*;
import java.util.Scanner;


public class HelloApplication extends Application {
PreparedStatement ps;
    public static Scanner scanner=new Scanner(System.in);
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Instancia el gestor de pantallas y muestra la pantalla inicial
        GestorDePantallas gestorDePantallas = new GestorDePantallas(primaryStage);
        gestorDePantallas.mostrarPantalla("Home");
    }
    public static Connection ConectarBD(String bd ){//el string es para saber el nombre de la base de datos
        // siempre y cuando esté dentro del mismo host y tenga los permisos (usuario y contraseña)
        Connection conexion;
        String host="jdbc:mysql://localhost/";
        String user="root";//usuario de la base de datos (por defecto es root)
        String pass= "Tinto56Ñ*52#";//contraseña de la base de datos
        System.out.println("Conectando...");
        try {
            conexion= DriverManager.getConnection(host+bd,user,pass);
            Statement statement=conexion.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM CITY");
            while (resultSet.next()){
                System.out.println(resultSet.getString("id")+" "+ "|"+resultSet.getString("name")+"|"+resultSet.getString("CountryCode")+"|"+resultSet.getString("District"));

            }
            conexion.close();
            statement.close();
            resultSet.close();
            System.out.println("Coneccion exitosa!!");
        }catch (SQLException e){
            PrintStream printStream = System.err;
            System.setErr(printStream);

            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conexion;
    }






    public static void main(String[] args) {

        ConectarBD("world");// nombre de la base de datos a la cual será conectada
        launch(args);
    }
}
