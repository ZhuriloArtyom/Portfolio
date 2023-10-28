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

public class AddController extends MainController{

    @FXML
    public TextField names;
    @FXML
    public TextField capitals;
    @FXML
    public TextField largestCity;

    public Button flag;
    public Button doneButton;
    @FXML
    public TextField neighbours;
    private String setFlag;



    public void setFlag() {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(flag.getScene().getWindow());
        if (file != null) {
            setFlag = file.getAbsolutePath();
        }

    }

    public void done() throws IOException {
        Country country = new Country(
                names.getText(), capitals.getText(),
                largestCity.getText(), neighbours.getText(), setFlag);
        countryList.add(country);
        addInCSV();
        Stage stage = (Stage) doneButton.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }
}