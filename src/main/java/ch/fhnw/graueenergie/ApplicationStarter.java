package ch.fhnw.graueenergie;

import ch.fhnw.graueenergie.controller.ButtonController;
import ch.fhnw.graueenergie.controller.Pi4JButtonController;
import ch.fhnw.graueenergie.controller.SerialScannerController;
import ch.fhnw.graueenergie.model.EnergyCalculatorModel;
import ch.fhnw.graueenergie.model.Model;
import ch.fhnw.graueenergie.view.DisplayView;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class ApplicationStarter extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TabPaneScreens.fxml"));

    Scene scene = new Scene(fxmlLoader.load());

    primaryStage.setTitle("Graue Energie");
    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true);
    primaryStage.show();

    DisplayView displayView = fxmlLoader.getController();
    ButtonController buttonController = new Pi4JButtonController(displayView);

    Model model = new EnergyCalculatorModel(displayView);
    new SerialScannerController(model, "/dev/ttyACM0").start();
    new SerialScannerController(model, "/dev/ttyACM1").start();
    buttonController.start();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
