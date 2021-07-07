package duke.controller;

import java.io.IOException;

import duke.ui.Messenger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Encapsulates a Menu class that represents the menu for the program.
 */
public class Menu {
    @FXML
    private VBox dukeLogo;
    @FXML
    private Label logoName;
    @FXML
    private Button startButton;

    /**
     * Initializes the Menu instance.
     */
    @FXML
    public void initialize() {
        logoName.setText(Messenger.LOGO);
    }

    /**
     * Switches the current scene to App.
     *
     * @throws IOException an IOException in trying to retrieve the fxml file.
     */
    @FXML
    public void switchToMain() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) startButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/view/App.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
