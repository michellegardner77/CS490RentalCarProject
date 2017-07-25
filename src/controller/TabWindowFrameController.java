package controller;

import DAL.RentalQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgard on 7/24/2017.
 */
public class TabWindowFrameController {

    private Customer customer;
    private CarRental carRental = new CarRental();


    public TableColumn<Object,Object> carSpecIDCol;
    public TableColumn<Object,Object> carSpecMakeCol;
    public TableColumn<Object,Object> carSpecModelCol;
    public TableColumn<Object,Object> carSpecYearCol;
    public TableColumn<Object,Object> carSpecSizeCol;
    public TableColumn<Object,Object> carSpecSelectCol;

    public TableColumn<Object,Object> rentedSelectCol;
    public TableColumn<Object,Object> rentedMakeCol;
    public TableColumn<Object,Object> rentedModelCol;
    public TableColumn<Object,Object> rentedYearCol;
    public TableColumn<Object,Object> rentedDateCol;

    public TableColumn<Object,Object> returnedCarsIDCol;
    public TableColumn<Object,Object> returnedCarsMakeCol;
    public TableColumn<Object,Object> returnedCarsModelCol;
    public TableColumn<Object,Object> returnedCarsYearCol;
    public TableColumn<Object,Object> returnedCarsRentedDateCol;
    public TableColumn<Object,Object> returnedCarsReturnedDateCol;


    public TableView<CarSpec> findCarTable;
    public TableView<Rental> rentedCarsTable;
    public TableView<Rental> returnedCarsTable;


    private ObservableList<CarSpec> findCarObservableList;
    private ObservableList<Rental> rentCarsObservableList;
    private ObservableList<Rental> returnedCarsObservableList;

    private RentalQueries rentalQueries = new RentalQueries();

    public TabWindowFrameController(Customer customer) {
        this.customer = customer;
    }


    // TODO: pass customer in for each table. So the table shows the cars the customer rented and returned

    public void findCarTabTable() {

//        carRental.findExistingCars();

        List<CarSpec> findCarSpecList = (List) rentalQueries.getCarSpecs("returned");
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


    public void rentCarsTabTable(){

        ArrayList rentCarsList = (ArrayList) rentalQueries.getCarSpecs("loanedOut");
        rentCarsObservableList = FXCollections.observableArrayList(rentCarsList);

        rentedMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        rentedModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        rentedYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        rentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rent_date"));
//        rentedSelectCol.setCellValueFactory(new PropertyValueFactory<>("select"));

        rentedCarsTable.setItems(rentCarsObservableList);

    }


    public void returnedCarsTable(){
        ArrayList returnedCarsList = (ArrayList) rentalQueries.getCarSpecs("returned");
        returnedCarsObservableList  =  FXCollections.observableList(returnedCarsList);

        returnedCarsIDCol.setCellValueFactory(new PropertyValueFactory<>("car_id"));
        returnedCarsMakeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        returnedCarsModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        returnedCarsYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        returnedCarsRentedDateCol.setCellValueFactory(new PropertyValueFactory<>("rent_date"));
        returnedCarsReturnedDateCol.setCellValueFactory(new PropertyValueFactory<>("return_date"));

        returnedCarsTable.setItems(returnedCarsObservableList);
    }


    private void rentSelectedButtonPressed(ActionEvent actionEvent) { // todo : this is a actionEvent trigger
//        CarSpec selectedCar = findCarTable.getSelectionModel.getSelectedItem();
//        String carId = selectedCar.getCarId();
        //rentalQueries.rentCar(carId);
        rentalQueries.getCarsToRent("returned");
    }
}
