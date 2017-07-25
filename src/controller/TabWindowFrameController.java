package controller;

import DAL.RentalQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.util.ArrayList;

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
    public TableView<CarSpec> findCarTable;
    public TableView<Rental> rentedCarsTable;
    public TableView<Rental> returnedCarsTable;


    private ObservableList<Customer> findCarObservableList;

    private RentalQueries rentalQueries = new RentalQueries();

    public TabWindowFrameController(Customer customer) {
        this.customer = customer;
    }

    public void findCarTabTable() {

//        carRental.findExistingCars();


        // get car spec
        ArrayList findCarSpecList = (ArrayList) rentalQueries.getCarSpecs("returned");
        // add customers to Observable List to populate table.
        findCarObservableList = FXCollections.observableList(findCarSpecList);
//        findCarTable
        //Setting Column titles the hard way!
        carSpecIDCol.setCellValueFactory(new PropertyValueFactory<>("Car ID"));
        carSpecMakeCol.setCellValueFactory(new PropertyValueFactory<>("Make"));
        carSpecModelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));
        carSpecYearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
        carSpecSizeCol.setCellValueFactory(new PropertyValueFactory<>("Size"));
        carSpecSelectCol.setCellValueFactory(new PropertyValueFactory<>("Select"));


        // initially set the table
        findCarSpecList.addAll(findCarObservableList);
    }
    private void rentSelectedButtonPressed(ActionEvent actionEvent) { // todo : this is a actionEvent trigger
        CarSpec selectedCar = findCarTable.getSelectionModel.getSelectedItem();
        String carId = selectedCar.getCarId();
        //rentalQueries.rentCar(carId);
        rentalQueries.getCarsToRent("returned");
    }
}
