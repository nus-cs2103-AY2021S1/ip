package focus;

import java.io.IOException;

import focus.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Represents a GUI for Focus using FXML. */
public class Main extends Application {
    /** Stage object to allow MainWindow class to access. */
    private static Stage stage;

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
        Focus focus = new Focus();
        try {
            String mainWindowFxml = "/view/MainWindow.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(mainWindowFxml));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setTitle("focus.");
            stage.getIcons().add(new Image("/images/planner.jpg"));
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFocus(focus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
