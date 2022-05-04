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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button enseignatnbtn;

    @FXML
    private Button matierebtn;

    @FXML
    private Button notebtn;

    @FXML
    private Button groupebtn;

    @FXML
    private TableView<Etudiant> studentTable;

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
    private TableColumn<Etudiant, Integer> age_id;

    @FXML
    private TableColumn<Etudiant, String> mail_id;

    ObservableList<Etudiant> list = FXCollections.observableArrayList(
            new Etudiant(12345678, "Mohamed", "Bennour", "H", 22, "m.bennour21232@pi.tn", 21232),
            new Etudiant(87654321, "Mohamed", "Youssef Nafkha", "H", 23, "m.youssef12345@pi.tn", 123456)

    );

    @FXML
    void enseignatn_click(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Enseignatns.fxml"));
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
        cin_id.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("cin"));
        numInsc_id.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("num_insc"));
        nom_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        prenom_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        sexe_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("sexe"));
        age_id.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("age"));
        mail_id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("mail"));

        studentTable.setItems(list);
    }
}
