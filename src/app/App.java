package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/app/MainView.fxml"));
        Parent mainRoot = mainLoader.load();
        stage.setTitle("Food Shop");
        stage.setScene(new Scene(mainRoot));
        stage.show();

        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/app/AdminView.fxml"));
        Parent adminRoot = adminLoader.load();
        Stage adminStage = new Stage();
        adminStage.setTitle("Admin Dashboard");
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();

        Controller mainController = mainLoader.getController(); // data from controller class
        AdminController adminCtrl = adminLoader.getController(); // data from admin class
        mainController.setAdminController(adminCtrl); // allow mainController to have access to adminCtrl
        adminCtrl.setDataForAdminController(mainController, mainController.getPositions(), mainController.getTotalRevenue()); // allows adminController to get current data
        adminCtrl.updateAllSoldLabels(mainController.getItemsSold());

    }

    public static void main(String[] args) {
        launch(args);
    }
}