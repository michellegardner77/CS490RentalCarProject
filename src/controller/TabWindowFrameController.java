package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CarSpec;

import java.net.URL;
import java.util.ResourceBundle;

public class TabWindowFrameController {
    private CarRental carRental = CarRental.getInstance();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="selectedCustomer0Label"
    private Label selectedCustomer0Label; // Value injected by FXMLLoader
    @FXML // fx:id="findCarTabPane"
    private Tab findCarTabPane; // Value injected by FXMLLoader
    @FXML // fx:id="findCarTable"
    private TableView<CarSpec> findCarTable; // Value injected by FXMLLoader
    @FXML // fx:id="findCarSearchButton"
    private Button findCarSearchButton; // Value injected by FXMLLoader
    @FXML // fx:id="findCarSearchTextField"
    private TextField findCarSearchTextField; // Value injected by FXMLLoader
    @FXML // fx:id="rentedCarsTabPane"
    private Tab rentedCarsTabPane; // Value injected by FXMLLoader
    @FXML // fx:id="rentedCarsTable"
    private TableView<?> rentedCarsTable; // Value injected by FXMLLoader
    @FXML // fx:id="rentedSelectCol"
    private TableColumn<?, ?> rentedSelectCol; // Value injected by FXMLLoader
    @FXML // fx:id="rentedMakeCol"
    private TableColumn<?, ?> rentedMakeCol; // Value injected by FXMLLoader
    @FXML // fx:id="rentedModelCol"
    private TableColumn<?, ?> rentedModelCol; // Value injected by FXMLLoader
    @FXML // fx:id="rentedYearCol"
    private TableColumn<?, ?> rentedYearCol; // Value injected by FXMLLoader
    @FXML // fx:id="rentedDateCol"
    private TableColumn<?, ?> rentedDateCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnSelectedButton"
    private Button returnSelectedButton; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsTabPane"
    private Tab returnedCarsTabPane; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsTable"
    private TableView<?> returnedCarsTable; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsIDCol"
    private TableColumn<?, ?> returnedCarsIDCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsMakeCol"
    private TableColumn<?, ?> returnedCarsMakeCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsModelCol"
    private TableColumn<?, ?> returnedCarsModelCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsYearCol"
    private TableColumn<?, ?> returnedCarsYearCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsRentedDateCol"
    private TableColumn<?, ?> returnedCarsRentedDateCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsReturnedDateCol"
    private TableColumn<?, ?> returnedCarsReturnedDateCol; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert selectedCustomer0Label != null :
                "fx:id=\"selectedCustomer0Label\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert findCarTabPane != null : "fx:id=\"findCarTabPane\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert findCarTable != null : "fx:id=\"findCarTable\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert findCarSearchButton != null : "fx:id=\"findCarSearchButton\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert findCarSearchTextField != null :
                "fx:id=\"findCarSearchTextField\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedCarsTabPane != null : "fx:id=\"rentedCarsTabPane\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedCarsTable != null : "fx:id=\"rentedCarsTable\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedSelectCol != null : "fx:id=\"rentedSelectCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedMakeCol != null : "fx:id=\"rentedMakeCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedModelCol != null : "fx:id=\"rentedModelCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedYearCol != null : "fx:id=\"rentedYearCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert rentedDateCol != null : "fx:id=\"rentedDateCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnSelectedButton != null :
                "fx:id=\"returnSelectedButton\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsTabPane != null : "fx:id=\"returnedCarsTabPane\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsTable != null : "fx:id=\"returnedCarsTable\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsIDCol != null : "fx:id=\"returnedCarsIDCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsMakeCol != null : "fx:id=\"returnedCarsMakeCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsModelCol != null :
                "fx:id=\"returnedCarsModelCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsYearCol != null : "fx:id=\"returnedCarsYearCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsRentedDateCol != null :
                "fx:id=\"returnedCarsRentedDateCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
        assert returnedCarsReturnedDateCol != null :
                "fx:id=\"returnedCarsReturnedDateCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";

        ObservableList<CarSpec> carSpecList = FXCollections.observableArrayList(carRental.getAvailableCars());
        initializeFindCarsTable(carSpecList);
    }

    private void initializeFindCarsTable(final ObservableList<CarSpec> list) {
        ObservableList<CarSpec> cars = FXCollections.observableArrayList(list);

        TableColumn<CarSpec, String> idCol = new TableColumn<CarSpec, String>("CarID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("carId")
        );

        TableColumn<CarSpec, String> makeCol = new  TableColumn<CarSpec, String>("Make");
        makeCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("make"));

        TableColumn<CarSpec, String> modelCol = new  TableColumn<CarSpec, String>("Model");
        modelCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("model")
        );

        TableColumn<CarSpec, String> yearCol = new  TableColumn<CarSpec, String>("Year");
        yearCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("year")
        );

        TableColumn<CarSpec, String> sizeCol = new  TableColumn<CarSpec, String>("Size");
        sizeCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("size")
        );

        findCarTable.getColumns().setAll(idCol, makeCol, modelCol, yearCol, sizeCol);
        findCarTable.setItems(list);
    }
}