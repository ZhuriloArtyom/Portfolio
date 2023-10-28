package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label officialName;

    @FXML
    private Label otherNames;
    @FXML
    private Label capital;
    @FXML
    private Label largestCity;

    @FXML
    public void initialize(){
        capital.setText("11");
    }
}