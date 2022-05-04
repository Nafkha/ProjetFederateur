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

public class EnseignatnsController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField cin_ens,cnss_ens,nom_ens,prenom_ens,age_ens,mail_ens;
    @FXML
    private RadioButton H,F;

    @FXML
    private TableView<Enseignant> tableEnseignant;
    @FXML
    private TableColumn<Enseignant, Integer> cin_id;
    @FXML
    private TableColumn<Enseignant, Integer> cnss_id;
    @FXML
    private TableColumn<Enseignant, String> nom_id;
    @FXML
    private TableColumn<Enseignant, String> prenom_id;
    @FXML
    private TableColumn<Enseignant, String> sexe_id;
    @FXML
    private TableColumn<Enseignant, Integer> age_id;
    @FXML
    private TableColumn<Enseignant, String> mail_id;
    ObservableList<Enseignant> list = FXCollections.observableArrayList();

    @FXML
    private Button ajouterEnseignant;

    @FXML
    private Button etudiantsbtn;

    @FXML
    private Button matierebtn;

    @FXML
    private Button notebtn;

    @FXML
    private Button groupebtn;

    @FXML
    void ajouter_click(ActionEvent event){
        int cin,age,cnss;
        String nom,prenom,email,sexe;
        cin = Integer.parseInt(cin_ens.getText());
        age = Integer.parseInt(age_ens.getText());
        cnss = Integer.parseInt(cnss_ens.getText());
        nom = nom_ens.getText();
        prenom =prenom_ens.getText();
        email = mail_ens.getText();
        if(H.isSelected()){
            sexe = "H";
        }else{
            sexe = "F";
        }
        try {
            PreparedStatement ajouterPersonne = App.con.prepareStatement("INSERT INTO PERSONNE VALUES(?,?,?,?,?,?)");
            ajouterPersonne.setInt(1,cin);
            String mail = nom.substring(0,1).toLowerCase()+"."+prenom.replaceAll(" ","").toLowerCase()+"@pi.tn";
            ajouterPersonne.setString(2,nom);
            ajouterPersonne.setString(3,prenom);
            ajouterPersonne.setString(4,mail);
            ajouterPersonne.setString(5,sexe);
            ajouterPersonne.setInt(6,age);
            PreparedStatement ajouterEnseignant = App.con.prepareStatement("INSERT INTO ENSEIGNANT VALUES(?,?)");
            ajouterEnseignant.setInt(1,cnss);
            ajouterEnseignant.setInt(2,cin);
            ajouterPersonne.execute();
            ajouterEnseignant.execute();
            list.add(new Enseignant(cin,nom,prenom,sexe,age,mail,cnss));


        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    @FXML
    void student_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Etudiants.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene =new Scene(root);
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
    void group_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Groupes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cin_id.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("cin"));
        cnss_id.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("cnss"));
        nom_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("nom"));
        prenom_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("prenom"));
        sexe_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("sexe"));
        age_id.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("age"));
        mail_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("mail"));
        try {
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("select personne.id, personne.nom, personne.prenom, personne.sexe,  personne.age, personne.mail,  enseignant.cnss from personne join enseignant on (personne.id = enseignant.id)");
            while(rs.next()){
                list.add(new Enseignant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableEnseignant.setItems(list);


    }
}
