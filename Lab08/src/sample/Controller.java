package sample;
import java.io.File;
import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
public class Controller extends GridPane{
    private DataSource mark;
    private File file;
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
    public void initialize(){
        sid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        ass.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        mid.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        fExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        fMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        lGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(mark.getAllMarks());
    }




}
