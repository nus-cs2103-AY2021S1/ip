package duke.gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String GREETINGS = "Woof! I'm your favourite Doggo!\n"
        + "How can I help you?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pug.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/corgi.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/bgimg.png"));
    private Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/send.png"));
    private ImageView sendLogo = new ImageView(sendIcon);

    /**
     * Initialises the new GUI window.
     */
    @FXML
    public void initialize() {
        setDialogBackground();

        //Button
        sendLogo.setFitHeight(20);
        sendLogo.setFitWidth(20);
        sendButton.setGraphic(sendLogo);

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(GREETINGS, dukeImage)

        );
    }

    public void setDialogBackground() {
        BackgroundImage bg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(0, 0, false, false, true, false));
        dialogContainer.setBackground(new Background(bg));
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
