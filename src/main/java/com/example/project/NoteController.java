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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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
    private TableView<Note> tableNote;
    @FXML
    private TableColumn<Note,String> mat_cl;
    @FXML
    private TableColumn<Note, Integer> student_cl;
    @FXML
    private TableColumn<Note, Double> note_cl;
    @FXML
    private TableColumn<Note, String> type_cl;


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
                listeNotes.add(new Note(matiere.getValue().toString(),Integer.parseInt(code_etu.getText()),note_etudiant,type.getValue().toString()));
                tableNote.refresh();
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
    private int index;
    public void selectionner()
    {
        index = tableNote.getSelectionModel().getSelectedIndex();
        mat_cl.setEditable(false);
        student_cl.setEditable(false);
        note_cl.setEditable(false);
        type_cl.setEditable(false);
        modifier.setDisable(false);
        if (index <= -1)
        {
            return;
        }

        code_etu.setText(student_cl.getCellData(index).toString());
        code_etu.setDisable(true);
        matiere.setValue(mat_cl.getCellData(index));
        matiere.setDisable(true);
        note.setText(note_cl.getCellData(index).toString());
        type.setValue(type_cl.getCellData(index));
        type.setDisable(true);


    }

    @FXML
    private Button modifier;

    public void modifier_click(){
        Double nv_note = Double.parseDouble(note.getText());
        if(nv_note>=0 && nv_note<=20){
            try {
                PreparedStatement pstmt = App.con.prepareStatement("UPDATE NOTE SET Note=? where(Mat=? and Etu=? and Type=?)");
                pstmt.setDouble(1,nv_note);
                pstmt.setString(2,matiere.getValue().toString());
                pstmt.setInt(3,Integer.parseInt(code_etu.getText()));
                pstmt.setString(4,type.getValue().toString());
                pstmt.execute();
                listeNotes.get(index).setNote(nv_note);
                tableNote.refresh();

            }catch (SQLException e){
                System.out.println("Erreur");
            }
        }

    }

    @FXML
    private TextField rechercher;
    public void recherche_data_par_num_etudiant()
    {
        rechercher.textProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                /*if (groupeRech.textProperty().get().isEmpty())
                {
                    tableEtudiant.setItems(list);
                    return;
                }*/
                ObservableList<Note> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<Note, ?>> column = tableNote.getColumns();

                for (int row = 0; row < listeNotes.size(); row++)
                {
                    for (int col = 1; col < 2; col++)
                    {
                        TableColumn colVar = column.get(col);
                        String cellVar = String.valueOf(colVar.getCellData(listeNotes.get(row)));
                        cellVar = cellVar.toLowerCase();
                        if (cellVar.contains(rechercher.getText().toLowerCase()) && cellVar.startsWith(rechercher.getText().toLowerCase()))
                        {
                            items.add(listeNotes.get(row));
                            break;
                        }
                    }
                }
                tableNote.setItems(items);
            }
        });
    }
    @FXML
    private TextField  rechercherMat;
    public void recherche_data_par_num_matiere()
    {
        rechercherMat.textProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                /*if (groupeRech.textProperty().get().isEmpty())
                {
                    tableEtudiant.setItems(list);
                    return;
                }*/
                ObservableList<Note> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<Note, ?>> column = tableNote.getColumns();

                for (int row = 0; row < listeNotes.size(); row++)
                {
                    for (int col = 0; col < 1; col++)
                    {
                        TableColumn colVar = column.get(col);
                        String cellVar = String.valueOf(colVar.getCellData(listeNotes.get(row)));
                        cellVar = cellVar.toLowerCase();
                        if (cellVar.contains(rechercherMat.getText().toLowerCase()) && cellVar.startsWith(rechercherMat.getText().toLowerCase()))
                        {
                            items.add(listeNotes.get(row));
                            break;
                        }
                    }
                }
                tableNote.setItems(items);
            }
        });
    }
    @FXML
    private Button export;

    @FXML
    void export_click(ActionEvent event) throws IOException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Notes");
        Map<String, Object[]> data
                = new TreeMap<String, Object[]>();
        data.put("1",new Object[]{"Numero Etudiant","Code Matiére","Note","Type"});

        /*for(int i=0;i<tableEtudiant.get;i++){
            String key = Integer.toString(i+2);
            data.put(key,new Object[]{cin_id.getCellData(i),numInsc_id.getCellData(i),});
        }*/
        int k=2;
        for (Note nt: tableNote.getItems()) {
            String key = Integer.toString(k);
            data.put(key,new Object[]{nt.getEtu(),nt.getMat(),String.valueOf(nt.getNote()),nt.getType()});
            k++;

        }
        Set<String> keyset = data.keySet();

        int rownum = 0;

        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);

            Object[] objArr = data.get(key);

            int cellnum = 0;
            sheet.setColumnWidth(0,10*256);
            sheet.setColumnWidth(1,10*256);
            sheet.setColumnWidth(2,10*256);
            sheet.setColumnWidth(3,10*256);
            for (Object obj : objArr) {

                Cell cell = row.createCell(cellnum++);
                CellStyle cellStyle = workbook.createCellStyle();

                cell.setCellStyle(cellStyle);
                if (obj instanceof String)
                    cell.setCellValue((String)obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try {
            String fileName = "AllNotes.xlsx";
            if(!rechercher.getText().isEmpty() && !rechercherMat.getText().isEmpty()){
                fileName = rechercher.getText()+"-"+rechercherMat.getText()+".xlsx";

            }else if(!rechercher.getText().isEmpty()){
                fileName = rechercher.getText()+".xlsx";
            }else if(!rechercherMat.getText().isEmpty()){
                fileName = rechercherMat.getText()+".xlsx";
            }


            FileOutputStream out = new FileOutputStream(
                    new File("C:\\Users\\nafkh\\Desktop\\ListeGroupes\\"+fileName));
            workbook.write(out);

            out.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    ObservableList<Note> listeNotes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        modifier.setDisable(true);
        mat_cl.setCellValueFactory(new PropertyValueFactory<Note, String>("mat"));
        student_cl.setCellValueFactory(new PropertyValueFactory<Note, Integer>("etu"));
        note_cl.setCellValueFactory(new PropertyValueFactory<Note, Double>("note"));
        type_cl.setCellValueFactory(new PropertyValueFactory<Note, String >("type"));
        try{
            PreparedStatement pstmt = App.con.prepareStatement("SELECT * FROM NOTE");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                listeNotes.add(new Note(rs.getString(1),rs.getInt(2),rs.getDouble(3),rs.getString(4)));
            }
            tableNote.setItems(listeNotes);

        }catch (SQLException e){

        }
    }
}
