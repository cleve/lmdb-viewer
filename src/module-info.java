module lmdbviewer {
    requires javafx.fxml;
    requires javafx.controls;
    requires lmdbjava;

    opens sample;
    exports sample;
}