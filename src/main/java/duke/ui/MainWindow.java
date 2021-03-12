package duke.ui;

import static duke.util.Keyword.WELCOME_MESSAGE;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String PATH_USER_IMG = "/images/user.png";
    private static final String PATH_USER_DUKE = "/images/duke.png";
    private static final String PATH_USER_JACK = "/images/jack.png";

    private final Image userImage = new Image(this.getClass().getResourceAsStream(PATH_USER_IMG));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream(PATH_USER_DUKE));
    private final Image centreImage = new Image(this.getClass().getResourceAsStream(PATH_USER_JACK));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView centrePic;
    @FXML
    private Label openingMessage;

    private Duke duke;

    /**
     * Initializes the main GUI screen.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes duke.
     *
     * @param duke Duke application.
     */
    protected void setDuke(Duke duke) {
        this.duke = duke;
        greetUser();
    }

    /**
     * Greets the user when the GUI opens.
     */
    private void greetUser() {
        centrePic.setImage(centreImage);
        openingMessage.setText(WELCOME_MESSAGE);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
