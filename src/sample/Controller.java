package sample;

import core.DataBase;
import core.KeyValue;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.event.TableModelEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ObservableList<KeyValue> list = FXCollections.observableArrayList();
    private File file;

    @FXML
    ToggleGroup radioSelector;

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
        // Enabling edition
        tableKeyValue.getSelectionModel().setCellSelectionEnabled(true);
        tableKeyValue.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

    public void copyField(ActionEvent actionEvent) {
        String elementTableSelected = tableKeyValue.getSelectionModel().getSelectedItem().getValueName();
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(elementTableSelected);
        clipboard.setContent(content);
    }

    public void copyField2(TableView<?> table) {
        StringBuilder clipboardString = new StringBuilder();

        ObservableList<TablePosition> positionList = table.getSelectionModel().getSelectedCells();

        int prevRow = -1;

        for (TablePosition position : positionList) {

            int row = position.getRow();
            int col = position.getColumn();

            Object cell = (Object) table.getColumns().get(col).getCellData(row);

            // null-check: provide empty string for nulls
            if (cell == null) {
                cell = "";
            }

            // determine whether we advance in a row (tab) or a column
            // (newline).
            if (prevRow == row) {

                clipboardString.append('\t');

            } else if (prevRow != -1) {

                clipboardString.append('\n');

            }

            // create string from cell
            String text = cell.toString();

            // add new item to clipboard
            clipboardString.append(text);

            // remember previous
            prevRow = row;
        }

        // create clipboard content
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(clipboardString.toString());

        // set clipboard content
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    public void searchValue(ActionEvent actionEvent) {
        // Selecting value to search
        RadioButton selectedRadioButton = (RadioButton) radioSelector.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        boolean valueSearch = false;
        if (toogleGroupValue.equals("Value")) {
            valueSearch = true;
        }
        String text = searchText.getText();
        DataBase dataBase = new DataBase(file);
        ArrayList <KeyValue> data = dataBase.searchData(text, valueSearch);
        tableKeyValue.getItems().clear();
        if (data.size() > 0) {
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

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
