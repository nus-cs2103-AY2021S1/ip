package src.main.java.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.java.duke.data.Duke;
import src.main.java.duke.storage.StorageFile;
import src.main.java.duke.storage.StorageFile.InvalidStorageFilePathException;
import src.main.java.duke.ui.MainWindow;

/**
 * Represents the GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        // Show welcome message and file path
        MainWindow ap = new MainWindow();
        ap.showWelcomeMessage();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("KirbyBot");
        try {
            StorageFile storage = new StorageFile();
            ap.setDuke(storage.load().setStorageFile(storage));
        } catch (StorageFile.StorageOperationException | InvalidStorageFilePathException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
