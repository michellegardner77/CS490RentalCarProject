package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by mgard on 7/25/2017.
 */
public class RentalDatesController {
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

    void iniitialize(){
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
                if(t != null){
                    return formatter.format(t);
                }
                return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !(string.trim().isEmpty())) {
                    return LocalDate.parse(string,formatter);
                }
                return null;
            }
        });

        datePicker.setOnAction(event-> {
            selectedDate = formatter.format(datePicker.getValue());
            returnDate = datePicker.getValue();
        });

        okayDateButton.setOnMouseClicked(event -> {
            final Node landingNode = (Node) event.getSource();
            final Stage landingStage = (Stage) landingNode.getScene().getWindow();
            landingStage.close();
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

    public void goToLastScene(ActionEvent actionEvent) {
        if(stage != null && prevScene != null){
            stage.setScene(prevScene);
        }
    }

}


