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
import java.util.ResourceBundle;

public class EnseignantsController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField cin_ens,cnss_ens,nom_ens,prenom_ens;
    @FXML
    private DatePicker date_naissance;

    @FXML
    private TextField recherche;

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
    private TableColumn<Enseignant, String> age_id;

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
    private Button modulebtn;

    @FXML
    private Button groupebtn;

    @FXML
    private Button absencebtn;

    @FXML
    void ajouter_click(ActionEvent event){
        int cin,cnss;
        String nom,prenom,sexe,date_n;
        cin = Integer.parseInt(cin_ens.getText());
        date_n = date_naissance.getValue().toString();
        cnss = Integer.parseInt(cnss_ens.getText());
        nom = nom_ens.getText();
        prenom =prenom_ens.getText();
        if(H.isSelected()){
            sexe = "H";
        }else{
            sexe = "F";
        }
        try {
            PreparedStatement ajouterPersonne = App.con.prepareStatement("INSERT INTO PERSONNE VALUES(?,?,?,?,?,?)");
            ajouterPersonne.setInt(1,cin);
            String mail = prenom.substring(0,1).toLowerCase()+"."+nom.replaceAll(" ","").toLowerCase()+"@pi.tn";
            ajouterPersonne.setString(2,nom);
            ajouterPersonne.setString(3,prenom);
            ajouterPersonne.setString(4,mail);
            ajouterPersonne.setString(5,sexe);
            ajouterPersonne.setString(6,date_n);
            PreparedStatement ajouterEnseignant = App.con.prepareStatement("INSERT INTO ENSEIGNANT VALUES(?,?)");
            ajouterEnseignant.setInt(1,cnss);
            ajouterEnseignant.setInt(2,cin);
            ajouterPersonne.execute();
            ajouterEnseignant.execute();
            list.add(new Enseignant(cin,nom,prenom,sexe,date_n,mail,cnss));


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

    public void recherche_data()
    {
        recherche.textProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                if (recherche.textProperty().get().isEmpty())
                {
                    tableEnseignant.setItems(list);
                    return;
                }
                ObservableList<Enseignant> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<Enseignant, ?>> column = tableEnseignant.getColumns();

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
                tableEnseignant.setItems(items);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cin_id.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("cin"));
        cnss_id.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("cnss"));
        nom_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("nom"));
        prenom_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("prenom"));
        sexe_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("sexe"));
        age_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("date_naissence"));
        mail_id.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("mail"));
        try {
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("select personne.id, personne.nom, personne.prenom, personne.sexe,  personne.date_naissence, personne.mail,  enseignant.cnss from personne join enseignant on (personne.id = enseignant.id)");
            while(rs.next()){
                list.add(new Enseignant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableEnseignant.setItems(list);
        System.out.println(list.toString());


    }
}
