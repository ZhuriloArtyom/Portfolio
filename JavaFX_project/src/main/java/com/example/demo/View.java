package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class View extends MainController{

    public View(String countryNames, String countryCapital, String countryLC, String countryNeighbours, String countryflag) {
        this.countryNames = countryNames;
        this.countryCapital = countryCapital;
        this.countryLC = countryLC;
        this.countryNeighbours = countryNeighbours;
        this.countryflag.set(countryflag);
    }

    String countryNames, countryCapital, countryLC, countryNeighbours;
    StringProperty countryflag = new SimpleStringProperty();

    @FXML
    private AnchorPane anch1;
    @FXML
    private AnchorPane anch2;
    @FXML
    private AnchorPane anch3;
    @FXML
    private AnchorPane anch4;
    @FXML
    private Label officialName;

    @FXML
    private Label otherNames;
    @FXML
    private Label capital;
    @FXML
    private Label largestCity;
    @FXML
    private ImageView flag;
    @FXML
    private Label neighbours;

    @FXML
    public void initialize() {

        Image flag1 = new Image(countryflag.get());
        if (countryNames.contains(";")) {
            anch1.getChildren().add(new Label("Other Names:"));
            otherNames.setText(countryNames.substring(countryNames.indexOf(';')+1));
            officialName.setText(countryNames.substring(0, countryNames.indexOf(';')));
        } else officialName.setText(countryNames);

        if (countryCapital.contains(";")) {
            anch2.getChildren().add(new Label("Capitals:"));
        }
        if (countryCapital.equals(countryLC) ) {
            anch2.getChildren().add(new Label("Capital and the largest city:"));
        } else {
            anch3.getChildren().add(new Label("The largest city:"));
            largestCity.setText(countryLC);
            anch2.getChildren().add(new Label("Capitals:"));
        }
        capital.setText(countryCapital);
        flag.setImage(flag1);
        if (countryNeighbours != "") {
            anch4.getChildren().add(new Label("Neighbours:"));
            neighbours.setText(countryNeighbours);
        }

    }
}
