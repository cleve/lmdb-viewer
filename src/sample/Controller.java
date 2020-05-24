package sample;

import core.DataBase;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    public void sayHelloWorld(ActionEvent actionEvent) {
        System.out.println("Click");
        DataBase dataBase = new DataBase("/home/mauricio/workspace/lab/python/varidb/app/storage/volume_st.db");

    }

    public void openDatabase(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        Stage stage = new Stage();
        fileChooser.showOpenDialog(stage);
    }

}
