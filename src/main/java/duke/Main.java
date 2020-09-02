package duke;

import java.io.IOException;

import duke.ui.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final Duke duke = new Duke();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load(); // AnchorPane is a node within the fxml file, so loads that node
            Scene scene = new Scene(ap); // place the nod onto the scene
            stage.setScene(scene); // set the scene on the stage
            fxmlLoader.<MainWindow>getController().setDuke(this.duke); // point the controller to Duke
            stage.show(); // show the stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
