package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ListController extends MainController{
    int[][] list = new int[5][5];
    int page_index = 0;

    @FXML
    public Button back;
    @FXML
    public Button forward;
    @FXML
    public Button returning;
    @FXML
    private VBox VBox1;

    @FXML
    private Label counterLabel;

    public ListController(int page_index) {
        this.page_index = page_index;
    }
    public ListController() {
    }

    public void initialize() {
        counterLabel.setText(page_index + 1 +"/5");
        int rows = 0;
        int pages = 0;
        for(Country country: countryList){
                list[pages][rows]= countryList.indexOf(country) + 1;
                rows++;
                if (rows == 5){
                    rows = 0;
                    pages++;
                }
                if(pages==5) break;
        }

        for(int i : list[page_index]){
            if (i!=0){
            AnchorPane pane = new AnchorPane();
            pane.setPrefHeight(80);
            Label label1 = new Label();
            AnchorPane.setLeftAnchor(label1,100.0);
            AnchorPane.setTopAnchor(label1,30.0 );
            ImageView imageView = new ImageView(countryList.get(i-1).getFlag());
            imageView.setFitHeight(50);
            imageView.setFitWidth(70);
            AnchorPane.setLeftAnchor(imageView,300.0);
            AnchorPane.setTopAnchor(imageView,15.0 );

                if (countryList.get(i-1).getNames().contains(";"))
                label1.setText(countryList.get(i-1).getNames().substring(0, countryList.get(i-1).getNames().indexOf(';')));
            else label1.setText(countryList.get(i-1).getNames());
            int finalI = i-1;
            pane.setOnMouseClicked(e -> {
                Stage stage = new Stage();
                FXMLLoader root = new FXMLLoader(getClass().getResource("View.fxml"));
                ViewController controller1 = new ViewController(finalI);
                root.setController(controller1);
                AnchorPane splitPane = null;
                try {
                    splitPane = root.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                Scene scene = new Scene(splitPane, 600, 400);
                stage.setScene(scene);
                stage.setTitle("List");
                stage.setScene(scene);
                stage.show();


            });

            VBox1.getChildren().add(pane);
            pane.getChildren().add(label1);
            pane.getChildren().add(imageView);

        }}}

    public void Return(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) returning.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.show();
    }


    public void Forward(ActionEvent actionEvent) throws IOException {
        if(page_index< 4){
            ChangePage(page_index + 1);
    }}

    public void Back(ActionEvent actionEvent) throws IOException {
        if(page_index > 0){

            ChangePage(page_index - 1);
    }}
    private void ChangePage(int page) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setResizable(false);
        ListController controller1 = new ListController(page);
        FXMLLoader root = new FXMLLoader(getClass().getResource("List.fxml"));
        root.setController(controller1);
        AnchorPane splitPane = null;
        try {
            splitPane = root.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Scene scene = new Scene(splitPane, 600, 400);
        stage.setTitle("List");
        stage.setScene(scene);
        stage.show();
    }
}
