package org.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Scanner;


public class HelloApplication extends Application {
//PreparedStatement ps;
    public static Scanner scanner=new Scanner(System.in);
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML y obtener el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        HelloController helloController = loader.getController();

        // Instanciar el gestor de pantallas y establecerlo en el controlador
        GestorDePantallas gestorDePantallas = new GestorDePantallas(primaryStage);
        helloController.setGestorDePantallas(gestorDePantallas);

        // Configurar la escena y mostrarla
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
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
