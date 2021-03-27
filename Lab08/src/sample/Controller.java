package sample;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class Controller extends GridPane{
    private DataSource mark;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn sid;

    @FXML
    private TableColumn ass;

    @FXML
    private TableColumn mid;

    @FXML
    private TableColumn fExam;

    @FXML
    private TableColumn fMark;

    @FXML
    private TableColumn lGrade;

    @FXML
    private TextField newSID;

    @FXML
    private TextField newAss;

    @FXML
    private TextField newMid;

    @FXML
    private TextField newFin;

    //stores the currently viewed file’s  filename
    String currentFilename = "current.csv";

    private File file = new File(currentFilename);

    @FXML
    public void initialize() throws FileNotFoundException {
        sid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        ass.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        mid.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        fExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        fMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        lGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(mark.getAllMarks());
       // System.out.println(tableView.getItems());

    }

    private void save() throws FileNotFoundException{
        // make a writer
        try (PrintWriter outputFile = new PrintWriter(file)) {

            // create empty student record object
            studentRecords records;

            // create a string builder to build the output string
            StringBuilder sb = new StringBuilder();

            // list to store studentRecord items from the table view
            ArrayList<studentRecords> list = new ArrayList<>();

            // loop through items in the table
            for (int x = 0; x < tableView.getItems().size(); x++) {

                //assign item from table to empty student record object
                records = (studentRecords) tableView.getItems().get(x);

                // add the record to the list
                list.add(records);
            }

            // loop through list to get each student record
            for (int i = 0; i < list.size(); i++) {

                // create each line in the output string with a comma at end until last line
                if (i < list.size() - 1) {
                    sb.append(list.get(i).getStudentId() + "," + list.get(i).getAssignment() + "," + list.get(i).getMidterm() + "," + list.get(i).getFinalExam() + ",");
                } else {
                    sb.append(list.get(i).getStudentId() + "," + list.get(i).getAssignment() + "," + list.get(i).getMidterm() + "," + list.get(i).getFinalExam());
                }

                sb.append("\n");
               }

            // write to file
            outputFile.write(sb.toString());
        }//(new File(currentFilename).getAbsolutePath());
    }


    public void handleExitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleSaveAsAction(ActionEvent actionEvent) throws FileNotFoundException {
        // open a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File loadFile = fileChooser.showOpenDialog(Main.window);
        currentFilename = loadFile.getAbsolutePath();

        // call save function
        save();
    }

    public void handleSaveAction(ActionEvent actionEvent) throws FileNotFoundException {
        save();
    }

    public void handleOpenAction(ActionEvent actionEvent) throws IOException {
        // open a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File loadFile = fileChooser.showOpenDialog(Main.window);
        currentFilename = loadFile.getAbsolutePath();

        // call load function
        load();
    }

    public void load() throws IOException {
        try (FileReader inputfile = new FileReader(file)) {
            // read the csv file
            BufferedReader input;
            input = new BufferedReader(inputfile);
            String line;
            ObservableList<studentRecords> loadmark = FXCollections.observableArrayList();

            // parse through file line by line
            while ((line = input.readLine()) != null) {

                // create array from each read line
                String studentValues[] = line.split(",");
                // add records to the obsevarbale Array list
                loadmark.add(new studentRecords(studentValues[0], Float.parseFloat(studentValues[1]), Float.parseFloat(studentValues[2]), Float.parseFloat(studentValues[3])));

            }
            tableView.setItems(loadmark);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleNewAction(ActionEvent actionEvent) {
        ObservableList<studentRecords> loadmark = FXCollections.observableArrayList();

        //set cell value factory

        sid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        ass.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        mid.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        fExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        fMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        lGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(loadmark);

    }

    public void handlebuttonAction(ActionEvent actionEvent) {

        // list to hold the contents of the table
        ObservableList<studentRecords> list = FXCollections.observableArrayList();

        // created empty student record object
        studentRecords records;

        // add contents from table to list
        for (int x = 0; x < tableView.getItems().size(); x++) {
            records = (studentRecords) tableView.getItems().get(x);
            list.add(records);
        }

        // add new student record to to list
        list.add(new studentRecords(newSID.getText(), Float.parseFloat(newAss.getText()), Float.parseFloat(newMid.getText()), Float.parseFloat(newFin.getText())));

        // set tableview items to list
        tableView.setItems(list);
    }
}
