package sample;

import core.DataBase;
import core.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ObservableList<KeyValue> list = FXCollections.observableArrayList();
    private File file;
    @FXML
    TextField searchText;

    @FXML
    private Label fileNameLabel;

    @FXML
    private TableView<KeyValue> tableKeyValue;

    @FXML
    private TableColumn<KeyValue, String> keyName;

    @FXML
    private TableColumn<KeyValue, String> valueName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyName.setCellValueFactory(new PropertyValueFactory<>("keyName"));
        valueName.setCellValueFactory(new PropertyValueFactory<>("valueName"));
        tableKeyValue.setItems(list);
    }

    public void openDatabase(ActionEvent actionEvent) {
        tableKeyValue.getItems().clear();
        Stage stage;
        String fileName;
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Select File");
        stage = new Stage();
        file = fileChooser.showDialog(stage);
        if (file == null) {
            fileNameLabel.setText("No file selected");
            return;
        }
        try {
            DataBase dataBase = new DataBase(file);
            ArrayList<KeyValue> data = dataBase.GetData();
            for (KeyValue keyVal: data) {
                this.list.add(keyVal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        fileName = file.getName();
        fileNameLabel.setText(fileName);
    }

    public void searchValue(ActionEvent actionEvent) {
        String text = searchText.getText();
        DataBase dataBase = new DataBase(file);
        ArrayList <KeyValue> data = dataBase.searchData(text);
        System.out.println(data);
        if (data.size() > 0) {
            tableKeyValue.getItems().clear();
            this.list.addAll(data);
        }
    }

    public void updateKeyValues(ActionEvent actionEvent) {
        try {
            tableKeyValue.getItems().clear();
            DataBase dataBase = new DataBase(file);
            ArrayList<KeyValue> data = dataBase.GetData();
            for (KeyValue keyVal: data) {
                this.list.add(keyVal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
