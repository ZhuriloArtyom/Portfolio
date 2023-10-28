package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController extends MainController{

    public ViewController(int Index) {
        this.countryNames = countryList.get(Index).getNames();
        this.countryCapital = countryList.get(Index).getCapitals();
        this.countryLC = countryList.get(Index).getLargestCity();
        this.countryNeighbours = countryList.get(Index).getNeighbours();
        this.countryflag.set(countryList.get(Index).getFlag());
        this.Index = Index;
    }
    int Index;
    String countryNames, countryCapital, countryLC, countryNeighbours;
    StringProperty countryflag = new SimpleStringProperty();



    @FXML
    private AnchorPane anchor1;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private AnchorPane anchor3;
    @FXML
    private AnchorPane anchor4;

    @FXML
    private AnchorPane NaNeAnchor;
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
    private Button editBtn;

    public void initialize() {
        Image flag1 = new Image(countryflag.get());
        if (countryNames.contains(";")) {
            anchor1.getChildren().add(new Label("Other Names:"));
            otherNames.setText(countryNames.substring(countryNames.indexOf(';')+1));
            officialName.setText(countryNames.substring(0, countryNames.indexOf(';')));
        } else officialName.setText(countryNames);

        if (countryCapital.contains(";")) {
            anchor2.getChildren().add(new Label("Capitals:"));
        }
        if (countryCapital.equals(countryLC) ) {
            anchor2.getChildren().add(new Label("Capital and the largest city:"));
        } else {
            anchor3.getChildren().add(new Label("The largest city:"));
            largestCity.setText(countryLC);
            anchor2.getChildren().add(new Label("Capitals:"));
        }
        capital.setText(countryCapital);
        flag.setImage(flag1);
        if (countryNeighbours != "") {
            anchor4.getChildren().add(new Label("Neighbours:"));
            neighbours.setText(countryNeighbours);
        }}




    public void editCountry(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) editBtn.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(getClass().getResource("Edit.fxml"));
        EditController controller1 = new EditController(Index);
        root.setController(controller1);
        AnchorPane splitPane = null;
        try {
            splitPane = root.load();
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }


        Scene scene = new Scene(splitPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Edit");
        stage.setScene(scene);
        stage.show();


    }
}
