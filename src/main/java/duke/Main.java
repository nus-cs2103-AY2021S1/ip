package src.main.java.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.main.java.duke.storage.StorageFile;
import src.main.java.duke.ui.MainWindow;

import src.main.java.duke.storage.StorageFile.InvalidStorageFilePathException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private src.main.java.duke.data.Duke duke = new src.main.java.duke.data.Duke();

    @Override
    public void start(Stage stage) {
        // Show welcome message and file path
        MainWindow ap = new MainWindow();
        ap.showWelcomeMessage();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("Best 2103/2103T bot");
        try {
            StorageFile storage = new StorageFile();
            ap.setDuke(storage.load().setStorageFile(storage));
        } catch (StorageFile.StorageOperationException | InvalidStorageFilePathException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
