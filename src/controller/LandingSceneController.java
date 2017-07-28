package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Customer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LandingSceneController {
    CarRental carRental = CarRental.getInstance();

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

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert customerTable != null : "fx:id=\"customerTable\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerNameCol != null : "fx:id=\"customerNameCol\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerTelephoneCol != null : "fx:id=\"customerTelephoneCol\" was not injected: check your FXML file 'landing.fxml'.";
        assert customerAddressCol != null : "fx:id=\"customerAddressCol\" was not injected: check your FXML file 'landing.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'landing.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'landing.fxml'.";
        assert rentCarButton != null : "fx:id=\"rentCarButton\" was not injected: check your FXML file 'landing.fxml'.";
        assert rentedCarButton != null : "fx:id=\"rentedCarButton\" was not injected: check your FXML file 'landing.fxml'.";

        ObservableList<Customer> customerList = FXCollections.observableArrayList(carRental.getAllCustomers());
        initializeCustomerTable(customerList);

        searchTextField.setOnKeyTyped(event -> {
            final ArrayList<Customer> customers = carRental.getCustomerContainingSubstring(searchTextField.getText());
            customerTable.setItems(FXCollections.observableArrayList(customers));
        });

        searchButton.setOnMouseClicked(event -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                System.out.println("No customer selected");
            } else {
                try {
                    carRental.setCurrentCustomer(selectedCustomer);
                    openTabPaneWindow(event);
                } catch (Exception e) {
                    System.out.println("Bad customer selected, controller threw error");
                }
            }
        });
    }


    // initializing , setting cols to these fields

    private void initializeCustomerTable(final ObservableList<Customer> list) {
        TableColumn<Customer, String> nameCol = new TableColumn<Customer, String>("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("name")
        );

        TableColumn<Customer, String> phoneCol = new TableColumn<Customer, String>("Phone Number");
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("phone"));

        TableColumn<Customer, String> addressCol = new TableColumn<Customer, String>("Address");
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("address")
        );

        customerTable.getColumns().setAll(nameCol, phoneCol, addressCol);
        customerTable.setItems(list);
    }

    private void openTabPaneWindow(final Event event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("../view/tabWindowFrame.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("TabWindow");
            stage.setScene(new Scene(root1));
            stage.show();
            final Node landingNode = (Node) event.getSource();
            final Stage landingStage = (Stage) landingNode.getScene().getWindow();
            landingStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
