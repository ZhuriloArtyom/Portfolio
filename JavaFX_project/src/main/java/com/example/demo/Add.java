package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Add extends MainController{

    @FXML
    public TextField names;
    @FXML
    public TextField capitals;
    @FXML
    public TextField largcit;
    @FXML
    public TextField neibours;
    public Button ggg;
    public Button donee;
    private String flagg;


    @FXML

    public void flagg() {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(ggg.getScene().getWindow());
        if (file != null) {
            flagg = file.getAbsolutePath();
        }

    }

    @FXML
    public void done() throws IOException {
        Country country = new Country(
                names.getText(), capitals.getText(),
                largcit.getText(), neibours.getText(), flagg);
        countryList.add(country);
        addInCSV();
        Stage stage = (Stage) donee.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.show();
    }
}