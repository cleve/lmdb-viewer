package sample;
import core.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    @FXML
    private Label fileNameLabel;

    public void openDatabase(ActionEvent actionEvent) {
        Stage stage;
        String fileName;
        File file;
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Select File");
        stage = new Stage();
        file = fileChooser.showDialog(stage);
        try {
            DataBase dataBase = new DataBase(file);
            System.out.println(dataBase.GetData());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        fileName = file.getName();
        fileNameLabel.setText(fileName);
    }


}
