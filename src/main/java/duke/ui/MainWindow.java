package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView sendImage;

    private Duke duke;

    // Icons made by <a href="http://www.freepik.com/" title="Freepik">Freepik</a>
    // from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/engineer.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/crm.jpg"));

    // Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a>
    // from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
    private Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/send.png"));


    /**
     * Initialize the scene
     */
    @FXML
    public void initialize() {
        sendImage.setImage(sendIcon);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Ui().showWelcome(), dukeImage, false)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Pair<String, Duke.ResponseType> responsePair = duke.getResponse(input);
        String response = responsePair.getKey();
        Duke.ResponseType type = responsePair.getValue();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, type == Duke.ResponseType.ERROR)
        );
        userInput.clear();
    }
}
