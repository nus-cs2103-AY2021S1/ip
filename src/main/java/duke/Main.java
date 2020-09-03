package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** A GUI for Focus using FXML. */
public class Main extends Application {
    /** Stage object to allow MainWindow class to access. */
    private static Stage stage;
    /** Creates Duke. */
    private final Duke duke = new Duke();

    /**
     * Gets the stage.
     *
     * @return Stage.
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Overrides start method of JavaFX.
     *
     * @param stage Stage.
     */
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("focus.");
            stage.getIcons().add(new Image("/images/planner.jpg"));
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
