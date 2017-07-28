package controller;

import DAL.RentalQueries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TabWindowFrameController {
    private CarRental carRental = CarRental.getInstance();
    private RentalQueries rentalQueries = new RentalQueries();

    public TableColumn<Object, Object> carSpecIDCol;
    public TableColumn<Object, Object> carSpecMakeCol;
    public TableColumn<Object, Object> carSpecModelCol;
    public TableColumn<Object, Object> carSpecYearCol;
    public TableColumn<Object, Object> carSpecSizeCol;
    public TableColumn<Object, Object> carSpecSelectCol;

//    public TableColumn<Object,Object> rentedSelectCol;
//    public TableColumn<Object,Object> rentedMakeCol;
//    public TableColumn<Object,Object> rentedModelCol;
//    public TableColumn<Object,Object> rentedYearCol;
//    public TableColumn<Object,Object> rentedDateCol;
//
//    public TableColumn<Object,Object> returnedCarsIDCol;
//    public TableColumn<Object,Object> returnedCarsMakeCol;
//    public TableColumn<Object,Object> returnedCarsModelCol;
//    public TableColumn<Object,Object> returnedCarsYearCol;
//    public TableColumn<Object,Object> returnedCarsRentedDateCol;
//    public TableColumn<Object,Object> returnedCarsReturnedDateCol;
//
//
//    public TableView<CarSpec> findCarTable;
//    public TableView<Rental> rentedCarsTable;
//    public TableView<Rental> returnedCarsTable;

    private Customer currentCustomer;
    private ObservableList<CarSpec> findCarObservableList;
    private ObservableList<Rental> rentCarsObservableList;
    private ObservableList<Rental> returnedCarsObservableList;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="selectedCustomerLabel"
    private Label selectedCustomerLabel; // Value injected by FXMLLoader
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
    private TableView<Rental> rentedCarsTable; // Value injected by FXMLLoader
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
    private TableView<Rental> returnedCarsTable; // Value injected by FXMLLoader
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
        assert selectedCustomerLabel != null :
                "fx:id=\"selectedCustomerLabel\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
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

        currentCustomer = carRental.getCurrentCustomer();
        if (currentCustomer == null) {
            throw new RuntimeException();
        } else {
            selectedCustomerLabel.setText(currentCustomer.getName() + "'s Account");
        }

        ObservableList<CarSpec> availableCars = FXCollections.observableArrayList(carRental.getAvailableCars());
        initializeCarSpecTable(availableCars, findCarTable);

        ObservableList<Rental> rentedCars = FXCollections.observableArrayList(carRental.getOutstandingRentalsForCustomer(currentCustomer));
        initializeRentalTable(rentedCars, rentedCarsTable);
    }

    private void initializeCarSpecTable(final ObservableList<CarSpec> list, final TableView<CarSpec> table) {
        TableColumn<CarSpec, String> idCol = new TableColumn<CarSpec, String>("CarID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("carId")
        );

        TableColumn<CarSpec, String> makeCol = new TableColumn<CarSpec, String>("Make");
        makeCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("make"));

        TableColumn<CarSpec, String> modelCol = new TableColumn<CarSpec, String>("Model");
        modelCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("model")
        );

        TableColumn<CarSpec, String> yearCol = new TableColumn<CarSpec, String>("Year");
        yearCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("year")
        );

        TableColumn<CarSpec, String> sizeCol = new TableColumn<CarSpec, String>("Size");
        sizeCol.setCellValueFactory(
                new PropertyValueFactory<CarSpec, String>("size")
        );

        table.getColumns().setAll(idCol, makeCol, modelCol, yearCol, sizeCol);
        table.setItems(list);
    }

    private void initializeRentalTable(final ObservableList<Rental> list, final TableView<Rental> table) {
        TableColumn<Rental, String> idCol = new TableColumn<Rental, String>("CarID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<Rental, String>("carId")
        );

        TableColumn<Rental, String> rentDateCol = new TableColumn<Rental, String>("Rent Date");
        rentDateCol.setCellValueFactory(
                new PropertyValueFactory<Rental, String>("rentDate"));


        table.getColumns().setAll(idCol, rentDateCol);
        table.setItems(list);
    }
//
//    public void findCarTabTable() {
//
//        //        carRental.findExistingCars();
//
//        List<CarSpec> findCarSpecList = (List) rentalQueries.getCarSpecs("returned");
//        // add customers to Observable List to populate table.
//        findCarTable = FXCollections.observableList(findCarSpecList);
//
//        // specify what properties to set hte table cols.
//        carSpecIDCol.setCellValueFactory(new PropertyValueFactory<>("car_id"));
//        carSpecMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
//        carSpecModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
//        carSpecYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
//        carSpecSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
//        //        carSpecSelectCol.setCellValueFactory(new PropertyValueFactory<>("Select"));
//
//
//        // initially set the table
//        findCarTable.setItems(findCarObservableList);
//    }
//
//
//    public void rentCarsTabTable(){
//
//        ArrayList rentCarsList = (ArrayList) rentalQueries.getCarSpecs("loanedOut");
//        ObservableList<CarSpec> rentCarsObservableList = FXCollections.observableArrayList(rentCarsList);
//
//        rentedMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
//        rentedModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
//        rentedYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
//        rentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rent_date"));
//        //        rentedSelectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
//
//        rentedCarsTable.setItems(rentCarsObservableList);
//
//    }
//
//
//    public void returnedCarsTable(){
//        ArrayList returnedCarsList = (ArrayList) rentalQueries.getCarSpecs("returned");
//        ObservableList<CarSpec> returnedCarsObservableList  =  FXCollections.observableList(returnedCarsList);
//
//        returnedCarsIDCol.setCellValueFactory(new PropertyValueFactory<>("car_id"));
//        returnedCarsMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
//        returnedCarsModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
//        returnedCarsYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
//        returnedCarsRentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rent_date"));
//        returnedCarsReturnedDateCol.setCellValueFactory(new PropertyValueFactory<>("return_date"));
//
//        returnedCarsTable.setItems(returnedCarsObservableList);
//    }
//
//
//    private void rentSelectedButtonPressed(ActionEvent actionEvent) { // todo : this is a actionEvent trigger
//        //        CarSpec selectedCar = findCarTable.getSelectionModel.getSelectedItem();
//        //        String carId = selectedCar.getCarId();
//        //rentalQueries.rentCar(carId);
//        rentalQueries.getCarsToRent("returned");
//    }
}
