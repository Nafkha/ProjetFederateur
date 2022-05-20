package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    static String admin;
    private String pw;

    @FXML
    private Button loginbtn;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private Label msg_id;

    @FXML
    void login_click(ActionEvent event) throws IOException
    {
        admin = user.getText();
        pw = pass.getText();
        try{
            PreparedStatement pstmt = App.con.prepareStatement("Select * from utilisateur where(Login = ? AND Password = ?)");
            pstmt.setString(1,admin);
            pstmt.setString(2,pw);
            ResultSet rs =   pstmt.executeQuery();
            if(rs.next()) {

                root = FXMLLoader.load(getClass().getResource("Etudiants.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
            }else{
                System.out.println("utilisateur n'existe pas");
                msg_id.setText("Identifiant ou mot de passe invalide");
            }

        }catch(SQLException e){
            System.out.println("User does not exist");
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}