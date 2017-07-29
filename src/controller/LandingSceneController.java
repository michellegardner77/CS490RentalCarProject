package controller;

import DAL.RentalQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LandingSceneController {
    CarRental carRental = CarRental.getInstance();
    RentalQueries rentalQueries = new RentalQueries();

    List<Customer> customerList = null;
    ObservableList<Customer> customerObservableList = null;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="customerTable"
    private TableView<Customer> customerTable; // Value injected by FXMLLoader
    @FXML // fx:id="customerNameCol"
    private TableColumn<?, ?> customerNameCol; // Value injected by FXMLLoader
    @FXML // fx:id="customerTelephoneCol"
    private TableColumn<?, ?> customerTelephoneCol; // Value injected by FXMLLoader
    @FXML // fx:id="customerAddressCol"
    private TableColumn<?, ?> customerAddressCol; // Value injected by FXMLLoader
    @FXML // fx:id="searchTextField"
    private TextField searchTextField; // Value injected by FXMLLoader
    @FXML // fx:id="searchButton"
    private Button searchButton; // Value injected by FXMLLoader
    @FXML // fx:id="rentCarButton"
    private Button rentCarButton; // Value injected by FXMLLoader
    @FXML // fx:id="rentedCarButton"
    private Button rentedCarButton; // Value injected by FXMLLoader

    // reference to stage
    private Stage stage;
    // reference to current parentScene
    private Scene parentScene;
    private Customer selectedCustomer;

    // This method is called by the FXMLLoader when initialization is complete
    @FXML
    void initialize() {
//        assert customerTable != null : "fx:id=\"customerTable\" was not injected: check your FXML file 'landing.fxml'.";
//        assert customerNameCol != null : "fx:id=\"customerNameCol\" was not injected: check your FXML file 'landing.fxml'.";
//        assert customerTelephoneCol != null : "fx:id=\"customerTelephoneCol\" was not injected: check your FXML file 'landing.fxml'.";
//        assert customerAddressCol != null : "fx:id=\"customerAddressCol\" was not injected: check your FXML file 'landing.fxml'.";
//        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'landing.fxml'.";
//        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'landing.fxml'.";
//        assert rentCarButton != null : "fx:id=\"rentCarButton\" was not injected: check your FXML file 'landing.fxml'.";
//        assert rentedCarButton != null : "fx:id=\"rentedCarButton\" was not injected: check your FXML file 'landing.fxml'.";

       //ObservableList<Customer> customerList = FXCollections.observableArrayList(carRental.getAllCustomers());
        loadCustomerTable();
        //initializeCustomerTable(customerList);

        searchTextField.setOnKeyTyped(event -> {
            if (searchTextField.getText() == null || searchTextField.getText().isEmpty()){
                searchCustomers();
            }
        });

        searchButton.setOnMouseClicked(event -> {
            searchCustomers();
        });

        rentCarButton.setOnMouseClicked(event -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                System.out.println("No customer selected");
            } else {
                try {
//                    carRental.setCurrentCustomer(selectedCustomer);
                    openTabPaneWindowFindCarsTab(event);
                } catch (Exception e) {
                    System.err.println("Bad customer selected, controller threw error");
                }
            }
        });

        rentedCarButton.setOnMouseClicked(event -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                System.out.println("No customer selected");
            } else {
                try {
//                    carRental.setCurrentCustomer(selectedCustomer);
                    openTabPaneWindowRentedCarsTab(event);
                } catch (Exception e) {
                    System.err.println("Bad customer selected, controller threw error");
                }
            }
        });

        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedCustomer = newSelection;
            }
        });
    }

     public void loadCustomerTable(){
        // Get list of customers from database
        customerList = rentalQueries.getAllCustomers();

        // create observableList (list that can change) to added data too then added the returned data from the database to the list
        customerObservableList= FXCollections.observableList(customerList);

        // map properties from list data (rows) to match a column in the Scene table columns
        // new PropertyValueFactory<>("name") this means name in the list object
        // if SQL returns a row called name and in the list is is called name then you want to map name to whatever column in the parentScene table
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerTelephoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        // tell the table to load the rows from the observable list created above
        customerTable.setItems(customerObservableList);
     }


    private void openTabPaneWindowFindCarsTab(final Event event) {
        try {
            // Load FXML file to FXMLoader
            FXMLLoader tabWindowFrameSceneLoader = new FXMLLoader(getClass().getResource("../view/tabWindowFrame.fxml"));

            // Create initial pane (anchorPane) for parentScene object.
            AnchorPane tabWindowsAnchorPane = tabWindowFrameSceneLoader.load();

            // Create parentScene
            Scene tabWindowScene = new Scene(tabWindowsAnchorPane);

            // Get the instance of the controller
            TabWindowFrameController tabWindowFrameController = tabWindowFrameSceneLoader.getController();
            tabWindowFrameController.setStage(stage);
            tabWindowFrameController.setPreviousScene(parentScene);
            tabWindowFrameController.setCurrentScene(tabWindowScene);

            tabWindowFrameController.selectFindCarTab();
            tabWindowFrameController.loadFindCarTabTable();
            tabWindowFrameController.setCurrentCustomer(selectedCustomer);
            tabWindowFrameController.setSelectedCustomerLabel();

            stage.setScene(tabWindowScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openTabPaneWindowRentedCarsTab(final Event event) {
        try {
            // Load FXML file to FXMLoader
        FXMLLoader tabWindowFrameSceneLoader = new FXMLLoader(getClass().getResource("../view/tabWindowFrame.fxml"));

        // Create initial pane (anchorPane) for parentScene object.
        AnchorPane tabWindowsAnchorPane = tabWindowFrameSceneLoader.load();

        // Create parentScene
        Scene tabWindowScene = new Scene(tabWindowsAnchorPane);

        // Get the instance of the controller
        TabWindowFrameController tabWindowFrameController = tabWindowFrameSceneLoader.getController();
        tabWindowFrameController.setStage(stage);
        tabWindowFrameController.setPreviousScene(parentScene);
        tabWindowFrameController.setCurrentScene(tabWindowScene);

        tabWindowFrameController.selectRentedCarsTab();
        tabWindowFrameController.setCurrentCustomer(selectedCustomer);
        tabWindowFrameController.setSelectedCustomerLabel();

        stage.setScene(tabWindowScene);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    public void onEnter(ActionEvent ae){
        searchCustomers();
    }

    private void searchCustomers(){
        if (searchTextField.getText() == null) {
            System.out.println("No search qualification.");
        } else {
            try {
                List<Customer> searchedCustomerList = rentalQueries.searchCustomers(searchTextField.getText());
                customerTable.setItems(FXCollections.observableArrayList(searchedCustomerList));

            } catch (Exception e) {
                System.err.println("Search Failed");
            }
        }
    }

    // Method to set the stage for reference
    public void setStage(Stage stage){
        this.stage=stage;
    }

    // Method to set the parent scene for reference. This is so we can go back to it.
    public void setParentScene(Scene parentScene){
        this.parentScene= parentScene;
    }
}
