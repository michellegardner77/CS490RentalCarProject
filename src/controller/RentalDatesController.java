package controller;

import DAL.RentalQueries;
import core.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mgard on 7/25/2017.
 */
public class RentalDatesController {
    RentalQueries rentalQueries = new RentalQueries();

    public Button okayDateButton;
    public DatePicker datePicker;
    public String selectedDate = null;
    public LocalDate returnDate = null;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy");
    // reference to stage
    private Stage stage;
    // reference to previous scene
    private Scene prevScene;
    // reference to current scene
    private Scene currentScene;

    private List<String> selectedCarIds;

    @FXML
    void initialize(){
        okayDateButton.setOnMouseClicked(event -> {
            goToLastScene();
        });
    }
    private void SetReturnDate(String returnDate){
        this.selectedDate = selectedDate;
    }

    public String getReturnDate(){
        return selectedDate;
    }

    // Method to set stage the current controller is running on
    void setStage(Stage stage){
        this.stage=stage;
    }

    // Method to set the current scene this controller
    void setCurrentScene(Scene currentScene){
        this.currentScene=currentScene;
    }

    // Method to set the last scene this controller can move back too
    void setPreviousScene(Scene prevScene){
        this.prevScene=prevScene;
    }

    public void goToLastScene() {
        if(stage != null && prevScene != null){
            LocalDate localDate = datePicker.getValue();
            if(datePicker.getValue() != null) {
                for (String carId: selectedCarIds) {
                    rentalQueries.rentSelectedCar(carId, datePicker.getValue());
                }

                FXMLLoader tabWindowFrameSceneLoader = new FXMLLoader(getClass().getResource("../view/tabWindowFrame.fxml"));
                // Get the instance of the controller
                TabWindowFrameController tabWindowFrameController = tabWindowFrameSceneLoader.getController();
                tabWindowFrameController.loadFindCarTabTable();
                tabWindowFrameController.loadRentedCarsTabTable();
                tabWindowFrameController.loadReturnedCarsTabTable();

            }
            stage.setScene(prevScene);
        }
    }

    public void setSelectedCarIds(List<String> selectedCarIds){
        this.selectedCarIds = selectedCarIds;
    }

}


