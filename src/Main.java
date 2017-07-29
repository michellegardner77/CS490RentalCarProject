import controller.CarRental;
import controller.LandingSceneController;
import core.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private static CarRental carRental = CarRental.getInstance();
    LandingSceneController landingcontroller;

    @Override
    public void start(Stage primaryStage) throws Exception {

        DBManager dbManager = new DBManager();

        FXMLLoader landingSceneFXMLLoader = new FXMLLoader(getClass().getResource("view/landing.fxml"));
        Parent root = landingSceneFXMLLoader.load();
        primaryStage.setTitle("Rental Cars");
        primaryStage.setResizable(false);
        Scene parentScene = new Scene(root, 600, 400);
        primaryStage.setScene(parentScene);

        LandingSceneController landingSceneController = landingSceneFXMLLoader.getController();
        // pass primary stage reference to LandingSceneController
        landingSceneController.setStage(primaryStage);
        landingSceneController.setParentScene(parentScene);

        primaryStage.show();
    }


    public static void main(String[] args){
        launch(args);
    }

}

