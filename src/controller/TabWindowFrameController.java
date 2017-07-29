package controller;

import DAL.RentalQueries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    private TableColumn rentedIdCol;

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


    @FXML // fx:id="findCarTabPane"
    private Tab findCarTab; // Value injected by FXMLLoader
    @FXML // fx:id="findCarSearchButton"
    private Button findCarSearchButton; // Value injected by FXMLLoader
    @FXML // fx:id="findCarSearchTextField"
    private TextField findCarSearchTextField; // Value injected by FXMLLoader
    @FXML // fx:id="rentedCarsTabPane"
    private Tab rentedCarsTab; // Value injected by FXMLLoader
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


    // This method is called by the FXMLLoader when initialization is complete
    @FXML
    void initialize() {
        findCarSearchTextField.setOnKeyTyped((KeyEvent event) -> {
            if(findCarSearchTextField.getText().isEmpty()){
                searchRentableCars();
            }
        });


        Parent root;
        rentSelectedButton.setOnMouseClicked(event -> {
            // this is a actionEvent trigger
            CarSpec selectedCar = findCarTable.getSelectionModel().getSelectedItem();
            RentalDatesController rentaldate = new RentalDatesController();
            openRentalDatesScene(event);
        });

        returnSelectedButton.setOnMouseClicked(event-> {
            searchRentableCars();
        });


        findCarTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        rentedCarsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
    }

    private void searchRentableCars(){
        if (findCarSearchTextField.getText() == null) {
            System.out.println("No search qualification.");
        } else {
            try {
                List<CarSpec> searchedCarSpec = rentalQueries.searchCarSpecs("RETURNED", findCarSearchTextField.getText());
                findCarTable.setItems(FXCollections.observableArrayList(searchedCarSpec));

            } catch (Exception e) {
                System.err.println("Search Failed");
            }
        }
    }

    private Date getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");

        Calendar cal = Calendar.getInstance();

        return java.sql.Date.valueOf(dateFormat.format(cal));

    }


    public void loadFindCarTabTable() {

        List<CarSpec> findCarSpecList = rentalQueries.getCarSpecs("RETURNED");
        // add customers to Observable List to populate table.
        findCarObservableList = FXCollections.observableList(findCarSpecList);

        // specify what properties to set hte table cols.
        carSpecIDCol.setCellValueFactory(new PropertyValueFactory<>("carId"));
        carSpecMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        carSpecModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        carSpecYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        carSpecSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));



        // initially set the table
        findCarTable.setItems(findCarObservableList);
    }


    // TODO: figure out what columns need to show on UI and setup query and model to get them.
    public void loadRentedCarsTabTable(){

        List rentCarsList =  rentalQueries.getRentedReturnedCars("loanedOut", currentCustomer.getName());
        rentCarsObservableList = FXCollections.observableArrayList(rentCarsList);

        rentedMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        rentedModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        rentedYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        rentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));

        rentedCarsTable.setItems(rentCarsObservableList);

    }

    // TODO: figure out what columns need to show on UI and setup query and model to get them.
    public void loadReturnedCarsTabTable(){
        List returnedCarsList = rentalQueries.getRentedReturnedCars("returned", currentCustomer.getName());
        returnedCarsObservableList  =  FXCollections.observableList(returnedCarsList);

        returnedCarsIDCol.setCellValueFactory(new PropertyValueFactory<>("carId"));
        returnedCarsMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        returnedCarsModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        returnedCarsYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        returnedCarsRentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        returnedCarsReturnedDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        returnedCarsTable.setItems(returnedCarsObservableList);
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
        loadFindCarTabTable();
    }

    public void selectRentedCarsTab(){
        int currentSelectedTabIndex = getSelectedTabIndex();

        if(currentSelectedTabIndex != 1){
            setSelectedTab(1);
        }

        // TODO load table on this tab
        loadRentedCarsTabTable();
    }


    public void selectReturnedCarsTab(){
        int currentSelectedTabIndex = getSelectedTabIndex();

        if(currentSelectedTabIndex != 2){
            setSelectedTab(2);
        }

        // TODO load table on this tab
        loadReturnedCarsTabTable();
    }




    private void openRentalDatesScene(final Event event) {
        try {

            FXMLLoader rentalDatesSceneLoader = new FXMLLoader(getClass().getResource("../view/rentalDates.fxml"));

            // Create initial pane (anchorPane) for parentScene object.
            AnchorPane rentalDateAnchorPane = rentalDatesSceneLoader.load();

            // Create parentScene
            Scene rentalDatesScene = new Scene(rentalDateAnchorPane);

            // Get the instance of the controller
            RentalDatesController rentalDatesController = rentalDatesSceneLoader.getController();

            rentalDatesController.setStage(stage);
            rentalDatesController.setPreviousScene(currentScene);
            rentalDatesController.setCurrentScene(rentalDatesScene);

            List<CarSpec> selectedCars = findCarTable.getSelectionModel().getSelectedItems();
            List<String> selectedCarIds= new ArrayList<>();
            for (CarSpec carSpec:selectedCars) {
                selectedCarIds.add(carSpec.getCarId());
            }

            rentalDatesController.setSelectedCarIds(selectedCarIds);

            stage.setScene(rentalDatesScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(ActionEvent ae){
        searchRentableCars();
    }

    //method to set the customer name to selectedCustomerLabel on the scene
    void setSelectedCustomerLabel(){
        selectedCustomerLabel.setText(currentCustomer.getName()+"'s Account");
    }

    // Method to set the last scene this controller can move back too
    void setCurrentCustomer(Customer currentCustomer){
        this.currentCustomer=currentCustomer;
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
                // clear out current customer
                stage.setScene(prevScene);
            }
    }
}




