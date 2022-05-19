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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MatiereController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Matiere> tableMatiere;
    @FXML
    private TableColumn<Matiere, String> code_mat;
    @FXML
    private TableColumn<Matiere, String> nom_matiere;
    @FXML
    private TableColumn<Matiere, Double> coef_matiere;
    @FXML
    private TableColumn<Matiere, Integer> ens_matiere;
    @FXML
    private TableColumn<Matiere,Integer> nb_notes;
    @FXML
    private TableColumn<Matiere, String> gm_mat;

    @FXML
    private TextField nom_mat;

    @FXML
    private TextField coef_mat;

    @FXML
    private TextField ens_mat;

    @FXML
    private ComboBox module_mat;

    @FXML
    private ComboBox nb_notes_mat;

    @FXML
    private Button ajouterMatiere;

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
    private Button absencebtn;

    ObservableList<Matiere> listeMatieres = FXCollections.observableArrayList();


    @FXML
    void ajouter_matiere_click(ActionEvent event) throws IOException{
        String nomMat = nom_mat.getText();
        String moduleMat = module_mat.getValue().toString();
        String idMatiere = nomMat + "-" +moduleMat;
        try{
            PreparedStatement pstmt = App.con.prepareStatement("INSERT INTO MATIERE VALUES(?,?,?,?,?,?)");
            pstmt.setString(1,idMatiere);
            pstmt.setString(2,nomMat);
            pstmt.setDouble(3,Double.parseDouble(coef_mat.getText()));
            pstmt.setInt(4,Integer.parseInt(ens_mat.getText()));
            pstmt.setString(5,moduleMat);
            pstmt.setInt(6,Integer.parseInt(nb_notes_mat.getValue().toString()));
            pstmt.execute();
            pstmt = App.con.prepareStatement("update groupemodule set coefGM=coefGM+? where(idGM=?)");
            pstmt.setDouble(1,Double.parseDouble(coef_mat.getText()));
            pstmt.setString(2,moduleMat);
            pstmt.execute();
            listeMatieres.add(new Matiere(idMatiere,nomMat,moduleMat,Double.parseDouble(coef_mat.getText()),Integer.parseInt(nb_notes_mat.getValue().toString()),Integer.parseInt(ens_mat.getText())));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        code_mat.setCellValueFactory(new PropertyValueFactory<Matiere,String>("idMat"));
        nom_matiere.setCellValueFactory(new PropertyValueFactory<Matiere, String>("nomMat"));
        coef_matiere.setCellValueFactory(new PropertyValueFactory<Matiere, Double>("coef"));
        ens_matiere.setCellValueFactory(new PropertyValueFactory<Matiere, Integer>("ens"));
        nb_notes.setCellValueFactory(new PropertyValueFactory<Matiere, Integer>("nbnotes"));
        gm_mat.setCellValueFactory(new PropertyValueFactory<Matiere,String>("gm"));

        ObservableList<Integer> nbNotes = FXCollections.observableArrayList(1,2,3);
        nb_notes_mat.setItems(nbNotes);
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idGM from GROUPEMODULE ");
            ObservableList<String> listmodule = FXCollections.observableArrayList();
            while(rs.next()){
                listmodule.add(rs.getString(1));
            }
            module_mat.setItems(listmodule);

        }catch (SQLException e){
            System.out.println(e);
        }
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MATIERE");
            while (rs.next()){
                listeMatieres.add(new Matiere(rs.getString(1),rs.getString(2),rs.getString(5),rs.getDouble(3),rs.getInt(6),rs.getInt(4)));
            }
            tableMatiere.setItems(listeMatieres);
        }catch (SQLException e){

        }

    }

}
