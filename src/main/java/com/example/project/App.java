package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

import java.sql.*;


public class App extends Application
{
    static String db = "jdbc:mysql://localhost:3306/projetjava";
    static String username = "root";
    static String password = "admin";
    static Connection con;
    @Override
    public void start(Stage stage) throws IOException
    {
        try{
            con = DriverManager.getConnection(db,username,password);
            System.out.println("Database connected");
        }catch (SQLException e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 674, 429);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}