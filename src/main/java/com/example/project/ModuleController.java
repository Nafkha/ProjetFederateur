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

public class ModuleController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Module> tableModule;
    @FXML
    private TableColumn<Module,String> id_gm;
    @FXML
    private TableColumn<Module,String> nom_id;
    @FXML
    private TableColumn<Module,Double> coef_id;
    @FXML
    private TableColumn<Module,String> grp_id;

    @FXML
    private Button etudiantsbtn;

    @FXML
    private Button enseignatnbtn;

    @FXML
    private Button notebtn;

    @FXML
    private Button groupebtn;

    @FXML
    private Button matierebtn;

    @FXML
    private Button absencebtn;
    @FXML
    private TextField nom_m;
    @FXML
    private TextField recherche;
    @FXML
    private ComboBox grp_m;
    @FXML
    private Button ajouter_m;




    ObservableList<Module> list = FXCollections.observableArrayList();

    @FXML
    void ajouter_m_click(ActionEvent event) throws IOException{
            try{
                String nom_module = nom_m.getText();
                String grp = grp_m.getValue().toString();
                String id_Module = nom_module + '-'+grp;
                PreparedStatement pstmt = App.con.prepareStatement("INSERT INTO GROUPEMODULE VALUES(?,?,0,?)");
                pstmt.setString(1,id_Module);
                pstmt.setString(2,nom_module);
                pstmt.setString(3,grp);
                pstmt.execute();
                list.add(new Module(id_Module,nom_module,grp,0));



            }catch (SQLException e){
                System.out.println("Ajout de groupe module echouée");
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
                    tableModule.setItems(list);
                    return;
                }
                ObservableList<Module> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<Module, ?>> column = tableModule.getColumns();
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
                tableModule.setItems(items);
            }
        });
    }

    public void selectionner()
    {
        int index = tableModule.getSelectionModel().getSelectedIndex();

        if (index <= -1)
        {
            return;
        }
        nom_m.setText(nom_id.getCellData(index));
        grp_m.setValue(grp_id.getCellData(index));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {


        ObservableList<String> listeGroupe = FXCollections.observableArrayList();
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct (idGrp) from GROUPE");
            while(rs.next()){
                listeGroupe.add(rs.getString(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        grp_m.setItems(listeGroupe);
        try{
            Statement stmt = App.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GROUPEMODULE");
            while (rs.next()){
                list.add(new Module(rs.getString(1),rs.getString(2),rs.getString(4),rs.getDouble(3)));
            }

        }catch (SQLException e){

        }
        id_gm.setCellValueFactory(new PropertyValueFactory<Module, String>("idgm"));
        nom_id.setCellValueFactory(new PropertyValueFactory<Module, String>("nom"));
        coef_id.setCellValueFactory(new PropertyValueFactory<Module, Double>("coef"));
        grp_id.setCellValueFactory(new PropertyValueFactory<Module, String>("grp"));

        tableModule.setItems(list);


    }
}
