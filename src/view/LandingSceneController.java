package view;// LandingSceneController or TabWindowController
// I'm not sure where to put it
// Random methods that need to be placed in the correct file. 
// key words may need to be changed to make it work.
// this is incomplete, a WIP and will need editing.
// 7/22/2017 

// note i switch between usercontroller (customer) and car controller since I am not sure what goes to what for this probject. 
// maybe this should be in a CustomerController landing scene since these methods are for going from a "login  page" to a tabbed table
// scene but with no specific tab selected.

import DAL.RentalQueries;
import controller.CarRental;
import controller.DBManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Car;
import model.CarSpec;
import model.Customer;

import java.awt.TextField;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class LandingSceneController extends Application{
    private AnchorPane userAnchorPane;
    RentalQueries rentalQueries = new RentalQueries();
    private Label selectedCustomerLabel;
    private Button findCarSearchButton;
    private Button rentSelectedButton;
    private TableView findCarTable;
    private DBManager dbManager;
    private Customer customer;
    private ArrayList<CarSpec> findCarSpecList;
    private ObservableList<CarSpec> findCarObservableList;
    // customer table cols
    public TableColumn<CarSpec, String> selectCarCol;
    public TableColumn<CarSpec, String> carIdCol;
    public TableColumn<CarSpec, String> carMakeCol;
    public TableColumn<CarSpec, String> carModelCol;
    public TableColumn<CarSpec, Year> carYearCol;
    public TableColumn<CarSpec, Enum> carSizeCol;
    public TextField searchTextField;
    public TableView<Customer> customerTable;
    public Button searchButton;
    public Button rentCarButton;
    public Button rentedCarButton;
    // customer table cols
    public TableColumn<Customer, String> customerNameCol;
    public TableColumn<Customer, String> customerTelephoneCol;
    public TableColumn<Customer, String> customerAddressCol;
    //List of customers
    private List<Customer> customerList;
    // ObservableList of customers to populate tables
    private ObservableList<Customer> customerObservableList;

    private void loadFindCarTable() {  // todo : this is a method

        // get customers
        findCarSpecList = (ArrayList) rentalQueries.getCarSpecs("returned");
        // add customers to Observable List to populate table.
        findCarObservableList = FXCollections.observableList(findCarSpecList);

        // specify what properties to set the table columns
        carIdCol.setCellValueFactory(new PropertyValueFactory<>("car_id"));
        carMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        carModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        carYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        carSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

        // initially set the table
        findCarTable.setItems(findCarObservableList);
    }

    public void loadCustomerTable() { // todo : this is a method

        DBManager dbManager = DBManager.getInstance();
        RentalQueries rentalQueries = new RentalQueries();

        // get customers
        customerList = rentalQueries.getAllCustomers();
        // add customers to Observable List to populate table.
        customerObservableList = FXCollections.observableList(customerList);

        // specify what properties to set the table columns
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_first_name"));
        customerTelephoneCol.setCellValueFactory(new PropertyValueFactory<>("customer_telephone"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customer_address"));

        // initially set the table
        customerTable.setItems(customerObservableList);
    }

    public void selectCustomer(ActionEvent actionEvent) { // todo : this is a actionEvent trigger

        // select item
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        ObservableList<Customer> customerSelected = new ObservableList<Customer>() {} allCustomers;
        for (Customer customer1 : customerList) {
            allCustomers.addAll(customerList);
        }
        ;
        customerSelected = customerList.getSelectionModel().getSelectedItems();

        // TODO:
        // Take the user that is selected and go to new Scene along with saving the name so it says "[name]'s acc"
        // change to new scene when "rent cars" or "return cars" button is clicked.
    }

    // set the text of the label to display customers account
    public void fillCustomerLabel(Customer customer) { // todo : this is a method
        selectedCustomerLabel.setText(customer.getName());
    }

    private void rentSelectedButtonPressed(ActionEvent actionEvent) { // todo : this is a actionEvent trigger
        CarSpec selectedCar = findCarTable.getSelectionModel.getSelectedItem();
        String carId = selectedCar.getCarId();
        //rentalQueries.rentCar(carId);
        rentalQueries.getCarsToRent("returned");
    }

    void setDbManager(DBManager dbManager) { // todo : this should not be here. The CarRental should handle db.
                                             // todo : use the methods in the CarRental controller
        this.dbManager = dbManager;
        final RentalQueries rentalQueries = new RentalQueries();
    }

    @Override
    public void start(Stage stage) {

        // TODO: still need to be able to select user from table and gather that customer info
        // assuming user clicked on their name and either Rented Cars or Rent Cars button
        Stage userStage = new Stage(); // should be the TabWindow stage with correct tab open
        userStage.setTitle("Title");
        userStage.setResizable(false);

        // STAGE contents (scenes)
        // load FXML file to FMXLLoarder (make sure file patch is correct)
        FXMLLoader userSceneLoader = new FXMLLoader(getClass().getResource(".../views/tabWindow.fmxl"));

        // Create the initial pane (anchor pane) for scene object
        try {
            userAnchorPane = userSceneLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //get instance of Car Controller to call it's methods to set properties
        CarRental carController = userSceneLoader.getController();

        // set db manager property on CarController (I'm assuming CarController because that's where we shall list all cars rented/to be
        // rented/etc.)
        carController.setDBManager(dbManager);
        //set customer property on CarController
        //carController.setCusomter(customer);

        // create new scene passing it the AnchorPane Object  ( I believe this just allows the programe to use the same Anchor pane
        // through out. I could be wrong.)
        Scene userScene = new Scene(userAnchorPane);

        // set the userStage to use the stage userStage
        // Think of the stage as the window with the "X" buttona dn the scene the contents inside
        userStage.setScene(userScene);

        // Refreash AllFiltered (may need an AllFiltered Car table for this) before showing scene.
        //granted we don't have filteres
        //carController.reloadAllFilteredCarTable();

        // Get current Stage and close it.
        // Get source node from ActionEvent passed to action method.
        Node source = (Node) actionEvent.getSource();
        // calling the getWindow method to get the tage. Have to case to Stage
        Stage landingStage = (Stage) source.getScene().getWindow();
        //close the window
        landingStage.close();

        // *this may be in a different controller, I'm not sure*

        // reloaded car table , maybe can be copied and changed up depending on tab
        // get cars and the car specs for car table
        findCarSpecList = (ArrayList) rentalQueries.getCarSpecs(customer.getName());
        //added cars to observable list to populate table
        findCarObservableList = FXCollections.observableList(cars);

        //Setting Column titles the hard way!
        carSpecIDCol.setCellValue(new PropertyValueFactory<>("Car ID"));
        carSpecMakeCol.setCellValue(new PropertyValueFactory<>("Make"));
        carSpecModelCol.setCellValue(new PropertyValueFactory<>("Model"));
        carSpecYearCol.setCellValue(new PropertyValueFactory<>("Year"));
        carSpecSizeCol.setCellValue(new PropertyValueFactory<>("Size"));
        carSpecSelectCol.setCellValue(new PropertyValueFactory<>("Select"));

        //Setting Column Titles for Users
        customerNameCol.setCellValue(new PropertyValueFactory<>("Name"));
        customerTelephoneCol.setCellValue(new PropertyValueFactory<>("Telephone"));
        customerAddressCol.setCellValue(new PropertyValueFactory<>("Address"));

        // initially set the table
        carSpec.setItems(myCarObservableList);
        customer.setItems(customerObservableList);
    }

    // when customer is selected, the the Rent Car button is pressed, it should take the user to a new scene.
    //    public void rentCarButtonPressed(ActionEvent actionEvent) {
    //        Stage rentCarTabStage = new Stage();
    //        rentCarTabStage.setTitle(customer.getName() + "'s Account");
    //        rentCarTabStage.setResizable(false);
    //
    //        //stage contents (scene)
    //        // load fxml to FXMLLoader
    //        FXMLLoader rentCarTabFXMLLoader = new FXMLLoader(getClass().getResource("../Scenes/CarTableScene.fxml")); // won't open
    //        to specific tab
    //
    //        // Create initial pane(AnchorPane) for scene object
    //        AnchorPane rentCarTabAnchorPane = rentCarTabFXMLLoader.load();
    //
    //        Get instance of cars to call it 's methods to set properties
    //
    //        // set db manager property on customerController
    //        CarRental carRental = new Car(dbManager);
    //
    //        // set customer property on customerController
    //        CarTableController.setCustomer(customer);
    //
    //        // Create new scene passing it the Anchorpane object
    //        Scene rentCarTabScene = new Scene(rentCarTabAnchorPane);
    //
    //        //set the rentCarTabStage
    //        rentCarTabAnchorPane.getScene(rentCarTabScene);
    //    }
}
