package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindowController. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ImageView dukeAvatar;
    @FXML
    private Label dukeMessage;
    @FXML
    private ImageView userAvatar;
    @FXML
    private Label userMessage;

    private Duke duke;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public void setUp(Duke duke, Stage stage) {
        this.duke = duke;
        this.stage = stage;
    }

    /**
     * Sets up things on start.
     */
    public void onStart() {
        duke.getGuiResponse().greet();
        dukeMessage.setText(duke.getGuiResponse().getResponse());
        dukeAvatar.setImage(dukeImage);
    }

    /**
     * Handles the user input.
     * @throws IOException
     */
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dukeMessage.setText(response);
        dukeAvatar.setImage(dukeImage);

        userMessage.setText(input);
        userAvatar.setImage(userImage);

        userInput.clear();

        if (duke.getState().getExitLoop()) {
            duke.onExit();
            stage.close();
        }
    }
}
