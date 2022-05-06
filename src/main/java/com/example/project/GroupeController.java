package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GroupeController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField idgrp,numgrp;
    @FXML
    private ComboBox niveaugrp,diplomegrp,specialitegrp;
    @FXML
    private Button ajoutergrp;

    @FXML
    private Button etudiantsbtn;

    @FXML
    private Button enseignatnbtn;

    @FXML
    private Button notebtn;

    @FXML
    private Button matierebtn;

    @FXML
    private Button modulebtn;

    @FXML
    private Button absencebtn;

    @FXML
    private ObservableList<String> specialite = FXCollections.observableArrayList(
      "Informatique",
      "Mecatronique",
      "Electrique"
    );
    @FXML
    private ObservableList<String> diplome = FXCollections.observableArrayList(
            "Préparatoire",
            "Ingénieur",
            "Licence",
            "Mastére"
    );

    @FXML
    void ajouter_grp(ActionEvent event) throws IOException{

        String idGrp,diplomeGrp,specialiteGrp,niveauGrp;
        int numGrp=0;

        diplomeGrp = diplomegrp.getValue().toString();
        specialiteGrp = specialitegrp.getValue().toString();
        niveauGrp = niveaugrp.getValue().toString();
        idGrp = diplomeGrp.substring(0,1)+specialiteGrp.substring(0,1)+niveauGrp;
        try {
            PreparedStatement pstmt = App.con.prepareStatement("SELECT COUNT(*) FROM GROUPE WHERE(idGrp = ?)");
            pstmt.setString(1,idGrp);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                numGrp = rs.getInt(1)+1;
            }

        }catch (SQLException e){

        }

        try{
            PreparedStatement pstmt = App.con.prepareStatement("INSERT INTO GROUPE VALUES(?,?,?,?,?)");
            pstmt.setString(1,idGrp);
            pstmt.setString(2,niveauGrp);
            pstmt.setString(3,diplomeGrp);
            pstmt.setString(4,specialiteGrp);
            pstmt.setInt(5,numGrp);
            pstmt.execute();
            System.out.println("Groupe ajoutée avec succées");

        }catch (SQLException e){

            e.printStackTrace();
        }


    }


    @FXML
    void student_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Etudiants.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void enseignatn_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Enseignants.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void matiere_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Matieres.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void module_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Modules.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void note_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Notes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void absence_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Absences.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    @FXML
    void onChange(ActionEvent event) throws IOException{
        ObservableList<Integer> niveau;

        System.out.println(diplomegrp.getValue());
        if(diplomegrp.getValue()=="Préparatoire" || diplomegrp.getValue()=="Mastére"){
            niveau = FXCollections.observableArrayList(
                    1,2
            );

        }else{
            niveau = FXCollections.observableArrayList(
                    1,2,3
            );
        }
        niveaugrp.setItems(niveau);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        specialitegrp.setItems(specialite);
        diplomegrp.setItems(diplome);





    }
}
