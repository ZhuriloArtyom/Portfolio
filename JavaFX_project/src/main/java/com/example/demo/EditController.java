package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class EditController extends MainController{


    public EditController(int Index) {
        this.index = Index;
    }
    int index;
    @FXML
    public ImageView currentFlag;
    @FXML

    public Label currentNames;
    @FXML

    public Label CurrentCapital;
    @FXML

    public Label currentLC;
    @FXML

    public Label currentNeighbours;
    public TextField newName;
    @FXML

    public TextField newCapital;
    @FXML

    public TextField newLC;
    @FXML

    public TextField newNeighbours;
    @FXML

    public Button flagBtn;
    @FXML

    private String changeFlag;
    @FXML

    private Button concludeBtn;



    public void changeFlag(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(flagBtn.getScene().getWindow());
        if (file != null) {
            changeFlag = file.getAbsolutePath();
        }

    }


    public void conclude(ActionEvent actionEvent) throws IOException {
        if(newName.getText() != ""){
            countryList.get(index).setNames(newName.getText());
        }
        if(newCapital.getText() != ""){
            countryList.get(index).setCapitals(newCapital.getText());
        }
        if(newLC.getText() != ""){
            countryList.get(index).setLargestCity(newLC.getText());
        }
        if(newNeighbours.getText() !=  ""){
            countryList.get(index).setNeighbours(newNeighbours.getText());
        }
        if(changeFlag!= null){
            countryList.get(index).setFlag(changeFlag);
        }

        addInCSV();

        Stage stage = (Stage) concludeBtn.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(getClass().getResource("View.fxml"));
        ViewController controller1 = new ViewController(index);
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
