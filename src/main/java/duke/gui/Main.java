package duke.gui;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the bridge between the logic in Duke and the GUI in MainWindow.
 */
public class Main extends Application {

    /** Title of the GUI. */
    private static final String TITLE = "Duke";

    /** Logic for Duke to run. */
    private final Duke duke = new Duke(true);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/images/DukeLogo.png"));
            stage.setTitle(Main.TITLE);
            fxmlLoader.<MainWindow>getController().setDukeGui(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a response from the input.
     *
     * @param input Input string
     * @return String describing the response to the input.
     */
    public String getResponse(String input) {
        return this.duke.getResponseForGui(input);
    }
}
