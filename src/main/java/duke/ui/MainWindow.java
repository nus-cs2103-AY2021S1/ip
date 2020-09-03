package duke.ui;

import duke.Duke;
import duke.Main;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/** Controller for MainWindow. Provides the layout for the other controls. */
public class MainWindow extends AnchorPane {
    /** Scroll pane for conservations. */
    @FXML
    private ScrollPane scrollPane;
    /** Dialog container for conversations. */
    @FXML
    private VBox dialogContainer;
    /** User's input. */
    @FXML
    private TextField userInput;
    /** Send button for user. */
    @FXML
    private Button sendButton;
    /** Duke. */
    private Duke duke;
    /** Number of times user has input their name. */
    private int numberOfTimes;
    /** Gets the image for User. */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/koya.png"));
    /** Gets the image for Focus. */
    private final Image focusImage = new Image(this.getClass().getResourceAsStream("/images/rj.png"));

    /** Initialises the main window. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UI.greetUser(), focusImage)
        );
    }

    /**
     * Sets Duke.
     *
     * @param duke Duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Focus' reply,
     * and appends them to the dialog container. Clears the user input after processing.
     * If user inputs "bye", Focus will close automatically after 5 seconds.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (numberOfTimes == 0) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(UI.addressUser(input), focusImage)
            );
            numberOfTimes++;
        } else {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, focusImage)
            );
            if (input.equals("bye")) {
                PauseTransition delay = new PauseTransition(Duration.seconds(5));
                delay.setOnFinished(event -> Main.getStage().close());
                delay.play();
            }
        }
        userInput.clear();
    }
}
