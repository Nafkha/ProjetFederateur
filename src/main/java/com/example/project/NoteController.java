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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NoteController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button etudiantsbtn;

    @FXML
    private Button enseignatnbtn;

    @FXML
    private Button matierebtn;

    @FXML
    private Button modulebtn;

    @FXML
    private Button groupebtn;
    @FXML
    private TextField code_etu;
    @FXML
    private ComboBox matiere;
    @FXML
    private ComboBox type;
    @FXML
    private TextField note;
    @FXML
    private Button ajouter;

    @FXML
    void ajouter_Click(ActionEvent event) throws IOException{
        Double note_etudiant = Double.parseDouble(note.getText().toString());
        if(note_etudiant>=0 && note_etudiant<=20){
            try{
                PreparedStatement pstmt = App.con.prepareStatement("INSERT INTO NOTE VALUES(?,?,?,?)");
                pstmt.setString(1,matiere.getValue().toString());
                pstmt.setInt(2,Integer.parseInt(code_etu.getText()));
                pstmt.setDouble(3,note_etudiant);
                pstmt.setString(4,type.getValue().toString());
                pstmt.execute();

            }catch (SQLException e){
                System.out.println("Erreur");
            }
        }
    }

    @FXML
    void onChange(ActionEvent event) throws IOException{
       int num_insc = Integer.parseInt(code_etu.getText());
              try{
            PreparedStatement pstmt = App.con.prepareStatement("SELECT grp from ETUDIANT where(num_insc = ?)");
            pstmt.setInt(1,num_insc);
            ResultSet rs = pstmt.executeQuery();
            String idGrp = "";
            while(rs.next()){
                idGrp = rs.getString(1);
            }
            pstmt = App.con.prepareStatement("SELECT idGM from groupemodule where(grp=?)");
            pstmt.setString(1,idGrp);
             rs = pstmt.executeQuery();
            ArrayList<String> lgm = new ArrayList<String>();
            while (rs.next()){
                lgm.add(rs.getString(1));
            }
           // ArrayList<String> lm = new ArrayList<String>();
                  ObservableList<String> lm = FXCollections.observableArrayList();
            for(int i =0;i<lgm.size();i++){
                pstmt= App.con.prepareStatement("SELECT idMat from matiere where gm=?");
                pstmt.setString(1,lgm.get(i));
                rs = pstmt.executeQuery();
                System.out.println(lgm.get(i));
                while (rs.next()){
                    lm.add(rs.getString(1));
                }
            }
            matiere.setItems(lm);



        }catch (SQLException e){
            System.out.println("Erreur");
        }
    }
    @FXML
    void matiere_onChange(ActionEvent event) throws IOException{
        String code_matiere = matiere.getValue().toString();
        System.out.println("Matiere : "+code_matiere);
        int nbnotes = 0;
        try{
            PreparedStatement pstmt = App.con.prepareStatement("SELECT nbnotes from matiere where idMat=? ");
            pstmt.setString(1,code_matiere);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                nbnotes = rs.getInt(1);
                System.out.println(nbnotes);
            }
            ObservableList<String> ltype ;
            if(nbnotes == 1){
                ltype = FXCollections.observableArrayList("Examen");
            }else  if(nbnotes == 2){
                ltype = FXCollections.observableArrayList("DS","Examen");

            }else{
                ltype = FXCollections.observableArrayList("DS","Examen","TP");

            }
            type.setItems(ltype);

        }catch (SQLException e){
            System.out.println("Erreur type");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}
