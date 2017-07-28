package controller;

import DAL.RentalQueries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TabWindowFrameController {

    private CarRental carRental = CarRental.getInstance();
    private RentalQueries rentalQueries = new RentalQueries();

    @FXML
    private Button backButton;

    @FXML
    private TabPane rentCarTabPane;

    @FXML
    private TableColumn<Object, Object> carSpecIDCol;
    @FXML
    private TableColumn<Object, Object> carSpecMakeCol;
    @FXML
    private TableColumn<Object, Object> carSpecModelCol;
    @FXML
    private TableColumn<Object, Object> carSpecYearCol;
    @FXML
    private TableColumn<Object, Object> carSpecSizeCol;
    @FXML
    private TableColumn<Object, Object> carSpecSelectCol;

    @FXML
    private TableColumn<Object,Object> rentedSelectCol;
    @FXML
    private TableColumn<Object,Object> rentedMakeCol;
    @FXML
    private TableColumn<Object,Object> rentedModelCol;
    @FXML
    private TableColumn<Object,Object> rentedYearCol;
    @FXML
    private TableColumn<Object,Object> rentedDateCol;
    @FXML
    private TableColumn<Object,Object> returnedCarsIDCol;
    @FXML
    private TableColumn<Object,Object> returnedCarsMakeCol;
    @FXML
    private TableColumn<Object,Object> returnedCarsModelCol;
    @FXML
    private TableColumn<Object,Object> returnedCarsYearCol;
    @FXML
    private TableColumn<Object,Object> returnedCarsRentedDateCol;
    @FXML
    private TableColumn<Object,Object> returnedCarsReturnedDateCol;

    @FXML
    private TableView<CarSpec> findCarTable;
    @FXML
    private TableView<Rental> rentedCarsTable;
    @FXML
    private TableView<Rental> returnedCarsTable;

    private Customer currentCustomer;
    private ObservableList<CarSpec> findCarObservableList;
    private ObservableList<Rental> rentCarsObservableList;
    private ObservableList<Rental> returnedCarsObservableList;

    @FXML
    private Label selectedCustomerLabel;
    @FXML
    private Button rentSelectedButton;

//
//    @FXML // ResourceBundle that was given to the FXMLLoader
//    private ResourceBundle resources;
//    @FXML // URL location of the FXML file that was given to the FXMLLoader
//    private URL location;
//    @FXML // fx:id="selectedCustomerLabel"
//    private Label selectedCustomerLabel; // Value injected by FXMLLoader
    @FXML // fx:id="findCarTabPane"
    private Tab findCarTab; // Value injected by FXMLLoader
//    @FXML // fx:id="findCarTable"
//    private TableView<CarSpec> findCarTable; // Value injected by FXMLLoader
    @FXML // fx:id="findCarSearchButton"
    private Button findCarSearchButton; // Value injected by FXMLLoader
    @FXML // fx:id="findCarSearchTextField"
    private TextField findCarSearchTextField; // Value injected by FXMLLoader
    @FXML // fx:id="rentedCarsTabPane"
    private Tab rentedCarsTab; // Value injected by FXMLLoader
//    @FXML // fx:id="rentedCarsTable"
//    private TableView<Rental> rentedCarsTable; // Value injected by FXMLLoader
//    @FXML // fx:id="rentedSelectCol"
//    private TableColumn<?, ?> rentedSelectCol; // Value injected by FXMLLoader
//    @FXML // fx:id="rentedMakeCol"
//    private TableColumn<?, ?> rentedMakeCol; // Value injected by FXMLLoader
//    @FXML // fx:id="rentedModelCol"
//    private TableColumn<?, ?> rentedModelCol; // Value injected by FXMLLoader
//    @FXML // fx:id="rentedYearCol"
//    private TableColumn<?, ?> rentedYearCol; // Value injected by FXMLLoader
//    @FXML // fx:id="rentedDateCol"
//    private TableColumn<?, ?> rentedDateCol; // Value injected by FXMLLoader
    @FXML // fx:id="returnSelectedButton"
    private Button returnSelectedButton; // Value injected by FXMLLoader
    @FXML // fx:id="returnedCarsTabPane"
    private Tab returnedCarsTab; // Value injected by FXMLLoader
    // reference to stage
    private Stage stage;
    // reference to previous scene
    private Scene prevScene;
    // reference to current scene
    private Scene currentScene;

    //    @FXML // fx:id="loadReturnedCarsTabTable"
//    private TableView<Rental> loadReturnedCarsTabTable; // Value injected by FXMLLoader
//    @FXML // fx:id="returnedCarsIDCol"
//    private TableColumn<?, ?> returnedCarsIDCol; // Value injected by FXMLLoader
//    @FXML // fx:id="returnedCarsMakeCol"
//    private TableColumn<?, ?> returnedCarsMakeCol; // Value injected by FXMLLoader
//    @FXML // fx:id="returnedCarsModelCol"
//    private TableColumn<?, ?> returnedCarsModelCol; // Value injected by FXMLLoader
//    @FXML // fx:id="returnedCarsYearCol"
//    private TableColumn<?, ?> returnedCarsYearCol; // Value injected by FXMLLoader
//    @FXML // fx:id="returnedCarsRentedDateCol"
//    private TableColumn<?, ?> returnedCarsRentedDateCol; // Value injected by FXMLLoader
//    @FXML // fx:id="returnedCarsReturnedDateCol"
//    private TableColumn<?, ?> returnedCarsReturnedDateCol; // Value injected by FXMLLoader
//
//    @FXML
//        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {


//        assert selectedCustomerLabel != null :
//                "fx:id=\"selectedCustomerLabel\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert findCarTabPane != null : "fx:id=\"findCarTabPane\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert findCarTable != null : "fx:id=\"findCarTable\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert findCarSearchButton != null : "fx:id=\"findCarSearchButton\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert findCarSearchTextField != null :
//                "fx:id=\"findCarSearchTextField\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedCarsTabPane != null : "fx:id=\"rentedCarsTabPane\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedCarsTable != null : "fx:id=\"rentedCarsTable\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedSelectCol != null : "fx:id=\"rentedSelectCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedMakeCol != null : "fx:id=\"rentedMakeCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedModelCol != null : "fx:id=\"rentedModelCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedYearCol != null : "fx:id=\"rentedYearCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert rentedDateCol != null : "fx:id=\"rentedDateCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnSelectedButton != null :
//                "fx:id=\"returnSelectedButton\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsTabPane != null : "fx:id=\"returnedCarsTabPane\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert loadReturnedCarsTabTable != null : "fx:id=\"loadReturnedCarsTabTable\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsIDCol != null : "fx:id=\"returnedCarsIDCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsMakeCol != null : "fx:id=\"returnedCarsMakeCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsModelCol != null :
//                "fx:id=\"returnedCarsModelCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsYearCol != null : "fx:id=\"returnedCarsYearCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsRentedDateCol != null :
//                "fx:id=\"returnedCarsRentedDateCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";
//        assert returnedCarsReturnedDateCol != null :
//                "fx:id=\"returnedCarsReturnedDateCol\" was not injected: check your FXML file 'tabWindowFrame.fxml'.";

        currentCustomer = carRental.getCurrentCustomer();
        if (currentCustomer == null) {
            throw new RuntimeException();
        } else {
            selectedCustomerLabel.setText(currentCustomer.getName() + "'s Account");
        }

        findCarSearchTextField.setOnKeyTyped((KeyEvent event) -> {
            final ArrayList<CarSpec> findCarSpecList = carRental.getCarSpecContainingSubstring(findCarSearchTextField.getText());
            findCarTable.setItems(FXCollections.observableArrayList(findCarSpecList));
        });


        rentSelectedButton.setOnMouseClicked(event -> {
            // this is a actionEvent trigger
            CarSpec selectedCar = findCarTable.getSelectionModel().getSelectedItem();


            RentalDatesController rentaldate = new RentalDatesController();
            //TODO: GET RETURN DATE FROM DATEPICKER WINDOW
            openRentalDatesScene(event);
            String returndate = rentaldate.getReturnDate();
            Date date = java.sql.Date.valueOf(returndate);
            rentalQueries.rentReturnSelectedCar(selectedCar.getCarId(), "loanedOut", getCurrentDate(), date);
            //update new tables with current data
            loadRentedCarsTabTable();
            loadReturnedCarsTabTable();
            loadFindCarTabTable();;

        });

        returnSelectedButton.setOnMouseClicked(event-> {
            Rental selectedCar = rentedCarsTable.getSelectionModel().getSelectedItem();

            rentalQueries.rentReturnSelectedCar(selectedCar.getCarId(), "returned", selectedCar.getRentDate(),getCurrentDate());
            loadRentedCarsTabTable();
            loadReturnedCarsTabTable();
            loadFindCarTabTable();
        });


    }
    private Date getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");

        Calendar cal = Calendar.getInstance();

        return java.sql.Date.valueOf(dateFormat.format(cal));

    }

//
//        ObservableList<CarSpec> availableCars = FXCollections.observableArrayList(carRental.getAvailableCars());
//        initializeCarSpecTable(availableCars, findCarTable);
//
//        ObservableList<Rental> rentedCars = FXCollections.observableArrayList(carRental.getOutstandingRentalsForCustomer(currentCustomer));
//        initializeRentalTable(rentedCars, rentedCarsTable);
//    }
//
//    private void initializeCarSpecTable(final ObservableList<CarSpec> list, final TableView<CarSpec> table) {
//        TableColumn<CarSpec, String> idCol = new TableColumn<CarSpec, String>("CarID");
//        idCol.setCellValueFactory(
//                new PropertyValueFactory<CarSpec, String>("carId")
//        );
//
//        TableColumn<CarSpec, String> makeCol = new TableColumn<CarSpec, String>("Make");
//        makeCol.setCellValueFactory(
//                new PropertyValueFactory<CarSpec, String>("make"));
//
//        TableColumn<CarSpec, String> modelCol = new TableColumn<CarSpec, String>("Model");
//        modelCol.setCellValueFactory(
//                new PropertyValueFactory<CarSpec, String>("model")
//        );
//
//        TableColumn<CarSpec, String> yearCol = new TableColumn<CarSpec, String>("Year");
//        yearCol.setCellValueFactory(
//                new PropertyValueFactory<CarSpec, String>("year")
//        );
//
//        TableColumn<CarSpec, String> sizeCol = new TableColumn<CarSpec, String>("Size");
//        sizeCol.setCellValueFactory(
//                new PropertyValueFactory<CarSpec, String>("size")
//        );
//
//        table.getColumns().setAll(idCol, makeCol, modelCol, yearCol, sizeCol);
//        table.setItems(list);
//    }
//
//    private void initializeRentalTable(final ObservableList<Rental> list, final TableView<Rental> table) {
//        TableColumn<Rental, String> idCol = new TableColumn<Rental, String>("CarID");
//        idCol.setCellValueFactory(
//                new PropertyValueFactory<Rental, String>("carId")
//        );
//
//        TableColumn<Rental, String> rentDateCol = new TableColumn<Rental, String>("Rent Date");
//        rentDateCol.setCellValueFactory(
//                new PropertyValueFactory<Rental, String>("rentDate"));
//
//
//        table.getColumns().setAll(idCol, rentDateCol);
//        table.setItems(list);
////    }

    private void loadFindCarTabTable() {

        //       carRental.findExistingCars();

        List<CarSpec> findCarSpecList = rentalQueries.getCarSpecs("returned");
        // add customers to Observable List to populate table.
        findCarObservableList = FXCollections.observableList(findCarSpecList);

        // specify what properties to set hte table cols.
        carSpecIDCol.setCellValueFactory(new PropertyValueFactory<>("car_id"));
        carSpecMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        carSpecModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        carSpecYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        carSpecSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        //        carSpecSelectCol.setCellValueFactory(new PropertyValueFactory<>("Select"));


        // initially set the table
        findCarTable.setItems(findCarObservableList);
    }


    // TODO: figure out what columns need to show on UI and setup query and model to get them.
    private void loadRentedCarsTabTable(){

//        List<Rental> rentCarsList =  rentalQueries.getCarSpecs("loanedOut");
//        rentCarsObservableList = FXCollections.observableArrayList(rentCarsList);
//
//        rentedMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
//        rentedModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
//        rentedYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
//        rentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rent_date"));
//        //        rentedSelectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
//
//        rentedCarsTable.setItems(rentCarsObservableList);

    }

    // TODO: figure out what columns need to show on UI and setup query and model to get them.
    private void loadReturnedCarsTabTable(){
//        List returnedCarsList = rentalQueries.getCarSpecs("returned");
//        returnedCarsObservableList  =  FXCollections.observableList(returnedCarsList);
//
//        returnedCarsIDCol.setCellValueFactory(new PropertyValueFactory<>("car_id"));
//        returnedCarsMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
//        returnedCarsModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
//        returnedCarsYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
//        returnedCarsRentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rent_date"));
//        returnedCarsReturnedDateCol.setCellValueFactory(new PropertyValueFactory<>("return_date"));
//
//        returnedCarsTable.setItems(returnedCarsObservableList);
    }

    // Method to change the tab selection
    private void setSelectedTab(int tabIndex){
        SingleSelectionModel<Tab> selectionModel = rentCarTabPane.getSelectionModel();
        selectionModel.select(tabIndex);
    }

    // Method to get the current selected tab index
    private int getSelectedTabIndex(){
        SingleSelectionModel<Tab> selectionModel = rentCarTabPane.getSelectionModel();
        return selectionModel.getSelectedIndex();
    }


    public void selectFindCarTab(){
        int currentSelectedTabIndex = getSelectedTabIndex();

        if(currentSelectedTabIndex != 0){
            setSelectedTab(0);
        }

        // TODO load table on this tab
    }

    public void selecRentedCarsTab(){
        int currentSelectedTabIndex = getSelectedTabIndex();

        if(currentSelectedTabIndex != 1){
            setSelectedTab(1);
        }

        // TODO load table on this tab
    }


    public void selectReturnedCarsTab(){
        int currentSelectedTabIndex = getSelectedTabIndex();

        if(currentSelectedTabIndex != 2){
            setSelectedTab(2);
        }

        // TODO load table on this tab
    }




    private void openRentalDatesScene(final Event event) {
        try {

            FXMLLoader rentalDatesSceneLoader = new FXMLLoader(getClass().getResource("../view/rentalDates.fxml"));

            Scene rentalDatesScene = rentalDatesSceneLoader.load();

            // Get the instance of the controller
            RentalDatesController rentalDatesController = rentalDatesSceneLoader.getController();

            rentalDatesController.setStage(stage);
            rentalDatesController.setPreviousScene(currentScene);
            rentalDatesController.setCurrentScene(rentalDatesScene);

            stage.setScene(rentalDatesScene);

//            final Node landingNode = (Node) event.getSource();
//            final Stage landingStage = (Stage) landingNode.getScene().getWindow();
            //landingStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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




