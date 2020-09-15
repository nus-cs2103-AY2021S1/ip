package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A GUI for Duke.
 */
public class Main extends Application {
    private Duke duke = new Duke("src/main/java/tasks.txt", "src/main/java/shortCuts.txt");

    /**
     * Implements the abstract start method in Application class
     *
     * @param stage it is to show the main screen
     */
    @Override
    public void start(Stage stage) {
        if (duke.isExit()) {
            stage.close();
        }
        try {
            stage.setTitle("Duke");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow ap = fxmlLoader.load();
            ap.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
