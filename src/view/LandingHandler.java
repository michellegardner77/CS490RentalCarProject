package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingHandler {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TitledPane landingFrame;
    @FXML
    private AnchorPane landingAnchorPane;
    @FXML
    private Button customerSearchButton;
    @FXML
    private Button customerRentedCarsSearchButton;
    @FXML
    private Button customerRentCarButton;
    @FXML
    private TextField customerSearchField;
    @FXML
    private TableView<?> customerInformationTable;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> telephoneColumn;
    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    void customerSearchButtonClicked(MouseEvent event) {

    }

    @FXML
    void findRentedCarsForCustomer(MouseEvent event) {

    }

    @FXML
    void rentACarForACustomer(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert landingFrame != null : "fx:id=\"landingFrame\" was not injected: check your FXML file 'landing.fxml'.";
        assert landingAnchorPane != null : "fx:id=\"landingAnchorPane\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerSearchButton != null : "fx:id=\"customerSearchButton\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerRentedCarsSearchButton != null :
                "fx:id=\"customerRentedCarsSearchButton\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerRentCarButton != null : "fx:id=\"customerRentCarButton\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerSearchField != null : "fx:id=\"customerSearchField\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerInformationTable != null :
                "fx:id=\"customerInformationTable\" was not injected: check your FXML file 'landing.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'landing.fxml'.";
        assert telephoneColumn != null : "fx:id=\"telephoneColumn\" was not injected: check your FXML file 'landing.fxml'.";
        assert addressColumn != null : "fx:id=\"addressColumn\" was not injected: check your FXML file 'landing.fxml'.";
    }
}


