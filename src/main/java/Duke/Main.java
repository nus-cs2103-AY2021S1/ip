package Duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke("src/main/java/tasks.txt");

    /**
     * Implements the abstract start method in Application class
     * @param stage it is to show the main screen
     */
    @Override
    public void start(Stage stage) {
        if(duke.isExit()){
            stage.close();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}