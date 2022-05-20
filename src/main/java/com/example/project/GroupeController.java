package com.example.project;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GroupeController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Groupe> tableGroupe;
    @FXML
    private TableColumn<Groupe, String> code_id;
    @FXML
    private TableColumn<Groupe, String> niveau_id;
    @FXML
    private TableColumn<Groupe, String> diplome_id;
    @FXML
    private TableColumn<Groupe, String> specialite_id;
    @FXML
    private TableColumn<Groupe, Integer> numgrp_id;

     ObservableList<Groupe> list = FXCollections.observableArrayList();


    @FXML
    private TextField idgrp,numgrp;
    @FXML
    private TextField recherche;
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
            list.add(new Groupe(idGrp,niveauGrp,diplomeGrp,specialiteGrp,numGrp));
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
    void group_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Groupes.fxml"));
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

    public void recherche_data()
    {
        recherche.textProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                if (recherche.textProperty().get().isEmpty())
                {
                    tableGroupe.setItems(list);
                    return;
                }
                ObservableList<Groupe> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<Groupe, ?>> column = tableGroupe.getColumns();
                for (int row = 0; row < list.size(); row++)
                {
                    for (int col = 0; col < column.size(); col++)
                    {
                        TableColumn colVar = column.get(col);
                        String cellVar = String.valueOf(colVar.getCellData(list.get(row)));
                        cellVar = cellVar.toLowerCase();
                        if (cellVar.contains(recherche.getText().toLowerCase()) && cellVar.startsWith(recherche.getText().toLowerCase()))
                        {
                            items.add(list.get(row));
                            break;
                        }
                    }
                }
                tableGroupe.setItems(items);
            }
        });
    }

    public void selectionner()
    {
        int index = tableGroupe.getSelectionModel().getSelectedIndex();

        if (index <= -1)
        {
            return;
        }
        diplomegrp.setValue(diplome_id.getCellData(index));
        specialitegrp.setValue(specialite_id.getCellData(index));
        niveaugrp.setValue(niveau_id.getCellData(index));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        specialitegrp.setItems(specialite);
        diplomegrp.setItems(diplome);
        code_id.setCellValueFactory(new PropertyValueFactory<Groupe, String>("idGrp"));
        niveau_id.setCellValueFactory(new PropertyValueFactory<Groupe, String>("niveau"));
        diplome_id.setCellValueFactory(new PropertyValueFactory<Groupe, String>("diplome"));
        specialite_id.setCellValueFactory(new PropertyValueFactory<Groupe, String>("specialite"));
        numgrp_id.setCellValueFactory(new PropertyValueFactory<Groupe, Integer>("num_grp"));
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GROUPE");
            while (rs.next()){
                list.add(new Groupe(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        tableGroupe.setItems(list);





    }
}
