package com.example.project;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbsenceController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField etu_field;

    @FXML
    private Button etudiantsbtn;

    @FXML
    private Button enseignatnbtn;

    @FXML
    private Button notebtn;

    @FXML
    private Button modulebtn;

    @FXML
    private Button groupebtn;
    @FXML
    private ComboBox mat_field;
    @FXML
    private DatePicker date_field;
    @FXML
    private ComboBox time_field;

    @FXML
    private TableView<Absence> absenceTable;
    @FXML
    private TableColumn<Absence,Integer> etudiant;
    @FXML
    private TableColumn<Absence,String> matiere;
    @FXML
    private TableColumn<Absence, String> date;
    @FXML
    private TableColumn<Absence,String> heure;



    @FXML
    private Button ajouter;

    @FXML
    void ajouter_click(ActionEvent event) throws IOException{
        try{
            PreparedStatement pstmt = App.con.prepareStatement("INSERT INTO absence values(?,?,?,?)");
            pstmt.setString(1,date_field.getValue().toString());
            pstmt.setString(2,mat_field.getValue().toString());
            pstmt.setInt(3,Integer.parseInt(etu_field.getText()));
            pstmt.setString(4,time_field.getValue().toString());
            pstmt.execute();
            listAbsences.add(new Absence(mat_field.getValue().toString(),date_field.getValue().toString(),time_field.getValue()
                    .toString(),Integer.parseInt(etu_field.getText())));
            absenceTable.refresh();

        }catch (SQLException e){

        }
    }

    @FXML
    public void fetch_matieres(){
        etu_field.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                try{
                    PreparedStatement pstmt = App.con.prepareStatement("SELECT grp from etudiant where(num_insc=?)");
                    pstmt.setInt(1,Integer.parseInt(etu_field.getText()));
                    String grp="";
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()){
                        grp=rs.getString(1);
                    }
                    pstmt = App.con.prepareStatement("SELECT idGM from groupemodule where(grp=?) ");
                    pstmt.setString(1,grp);
                     rs = pstmt.executeQuery();
                    ArrayList<String> lgm = new ArrayList<String>();
                    while(rs.next()){
                        lgm.add(rs.getString(1));
                    }
                    ObservableList<String> lm = FXCollections.observableArrayList();
                    for(String gm:lgm){
                        pstmt = App.con.prepareStatement("select distinct (idMat) from matiere where(gm=?)");
                        pstmt.setString(1,gm);
                        System.out.println("Groupe Modul"+gm);
                        rs=pstmt.executeQuery();
                        while (rs.next()){
                            lm.add(rs.getString(1));
                        }
                    }
                    mat_field.setItems(lm);

                }catch (SQLException e){

                }

            }
        });
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
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene =new Scene(root);
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
    ObservableList<Absence> listAbsences = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ABSENCE");
            while (rs.next()){
                listAbsences.add(new Absence(rs.getString(2),rs.getString(1),rs.getString(4),rs.getInt(3)));
            }

        }catch (SQLException e){

        }
        ObservableList<String> time_list = FXCollections.observableArrayList("9","10","13","14");
        time_field.setItems(time_list);
        etudiant.setCellValueFactory(new PropertyValueFactory<Absence,Integer>("etu"));
        matiere.setCellValueFactory(new PropertyValueFactory<Absence,String>("mat"));
        date.setCellValueFactory(new PropertyValueFactory<Absence,String>("h"));
        heure.setCellValueFactory(new PropertyValueFactory<Absence,String>("t"));

        absenceTable.setItems(listAbsences);



    }

}
