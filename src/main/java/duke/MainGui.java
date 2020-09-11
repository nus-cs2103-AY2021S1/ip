package duke;

import java.io.IOException;

import duke.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainGui extends Application {
    private Duke hal9000 = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            // setting up Hal9000
            hal9000.loadPrevTasks();

            FXMLLoader fxmlLoader = new FXMLLoader(MainGui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(hal9000); // using controller!! Important part
            fxmlLoader.<MainWindow>getController().welcomeUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
