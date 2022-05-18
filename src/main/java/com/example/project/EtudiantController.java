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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField cin_etu;

    @FXML
    private  TextField nom_etu;

    @FXML
    private TextField prenom_etu;

    @FXML
    private TextField mail_etu;

    @FXML
    private TextField age_etu;

    @FXML
    private DatePicker date_naissance;

    @FXML
    private ComboBox groupe_etu;
    @FXML
    private ComboBox groupe_etu1;

    @FXML
    private TextField recherche;

    @FXML
    private RadioButton H,F;

    @FXML
    private Button enseignatnbtn;

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
    private Button ajouter;

    @FXML
    private TableView<Etudiant> tableEtudiant;

    @FXML
    private TableColumn<Etudiant, Integer> cin_id;

    @FXML
    private TableColumn<Etudiant, Integer> numInsc_id;

    @FXML
    private TableColumn<Etudiant, String> nom_id;


    @FXML
    private TableColumn<Etudiant, String> prenom_id;


    @FXML
    private TableColumn<Etudiant, String> sexe_id;

    @FXML
    private TableColumn<Etudiant, String> date_id;

    @FXML
    private TableColumn<Etudiant, String> mail_id;
    @FXML
    private TableColumn<Etudiant, String> groupe_id;
    @FXML
    private TableColumn<Etudiant, Integer> numgrp_id;
    ObservableList<Etudiant> list = FXCollections.observableArrayList();

    @FXML
    void ajouter_clik(ActionEvent event) throws IOException{
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String y = String.valueOf(year);
        int nbEtudiant = 0;
        try{
            PreparedStatement pstmt = App.con.prepareStatement("SELECT count(*) from etudiant where(data_insc=?)");
            pstmt.setInt(1,year);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                nbEtudiant = rs.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        String num_insc = y.substring(2,4);
        if(nbEtudiant<10){
            num_insc += "00"+String.valueOf(nbEtudiant);
        }else if(nbEtudiant<100 && nbEtudiant>=10){
            num_insc+="0"+String.valueOf(nbEtudiant);
        }else{
            num_insc+=String.valueOf(nbEtudiant);
        }
        try{
            PreparedStatement ajouterPersonne = App.con.prepareStatement("INSERT INTO PERSONNE VALUES(?,?,?,?,?,?)");
            String sexe;
            if(H.isSelected()){
                sexe ="H";
            }else{
                sexe = "F";
            }
            String date_n = date_naissance.getValue().toString();
            System.out.println(date_n);
            String mail = prenom_etu.getText().substring(0,1).toLowerCase(Locale.ROOT)+"."+nom_etu.getText().replaceAll(" ","").toLowerCase(Locale.ROOT)+num_insc+"@pi.tn";
            ajouterPersonne.setInt(1,Integer.parseInt(cin_etu.getText()));
            ajouterPersonne.setString(2,nom_etu.getText());
            ajouterPersonne.setString(3,prenom_etu.getText());
            ajouterPersonne.setString(4,mail);
            ajouterPersonne.setString(5,sexe);
            ajouterPersonne.setString(6,date_n);
            ajouterPersonne.execute();
            PreparedStatement ajouterEtudiant = App.con.prepareStatement("INSERT INTO ETUDIANT VALUES(?,?,?,?,?)");
            ajouterEtudiant.setInt(2,Integer.parseInt(cin_etu.getText()));
            ajouterEtudiant.setString(1,num_insc);
            ajouterEtudiant.setString(3,groupe_etu.getValue().toString());
            ajouterEtudiant.setInt(4,year);
            ajouterEtudiant.setInt(5,Integer.parseInt(groupe_etu1.getValue().toString()));
            ajouterEtudiant.execute();
            list.add(new Etudiant(Integer.parseInt(cin_etu.getText()),nom_etu.getText(), prenom_etu.getText(),sexe,date_n,mail,Integer.parseInt(num_insc),groupe_etu.getValue().toString(),Integer.parseInt(groupe_etu1.getValue().toString())));


        }catch (SQLException e){
            e.printStackTrace();

        }

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

    public void recherche_data()
    {
        recherche.textProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                if (recherche.textProperty().get().isEmpty())
                {
                    tableEtudiant.setItems(list);
                    return;
                }
                ObservableList<Etudiant> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<Etudiant, ?>> column = tableEtudiant.getColumns();

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
                tableEtudiant.setItems(items);
            }
        });
    }

    public void selectionner()
    {
        int index = tableEtudiant.getSelectionModel().getSelectedIndex();

        if (index <= -1)
        {
            return;
        }

        cin_etu.setText(cin_id.getCellData(index).toString());
        nom_etu.setText(nom_id.getCellData(index));
        prenom_etu.setText(prenom_id.getCellData(index));

        if (sexe_id.getCellData(index).equals("H"))
        {
            H.fire();
        }
        else
        {
            F.fire();
        }
        LocalDate ld = LocalDate.parse(date_id.getCellData(index));
        date_naissance.setValue(ld);
        groupe_etu.setValue(groupe_id.getCellData(index));

    }
    @FXML
    void onChange(ActionEvent event) throws IOException{
        try{
            PreparedStatement pstmt = App.con.prepareStatement("SELECT num_g from GROUPE where(idGrp = ?) ");
            ObservableList<Integer> num_grp_list = FXCollections.observableArrayList();
            pstmt.setString(1,groupe_etu.getValue().toString());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                num_grp_list.add(rs.getInt(1));

            }
            groupe_etu1.setItems(num_grp_list);

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erreur de query");

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cin_id.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("cin"));
        numInsc_id.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("num_insc"));
        nom_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        prenom_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        sexe_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("sexe"));
        date_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("date_naissence"));
        mail_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("mail"));
        groupe_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("grp"));
        numgrp_id.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("num_grp"));


        try{
            PreparedStatement pstmt = App.con.prepareStatement("SELECT distinct (idGrp) from GROUPE");
            ObservableList<String> listeGroupes = FXCollections.observableArrayList();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                listeGroupes.add(rs.getString(1));
            }
            groupe_etu.setItems(listeGroupes);

        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("select personne.id, etudiant.num_insc ,personne.nom, personne.prenom, personne.sexe,  personne.date_naissence, personne.mail, etudiant.grp, etudiant.num_grp from personne join etudiant on (personne.id = etudiant.id)");
            while(rs.next()){
                list.add(new Etudiant(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(2),rs.getString(8),rs.getInt(9)));
            }


        }catch(SQLException e){
            e.printStackTrace();
        }


        tableEtudiant.setItems(list);
    }
}
