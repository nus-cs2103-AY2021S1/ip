package duke.application;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/app/cutecat.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/app/chubbycat.jpeg"));

    /**
     * Initialize the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setBackground(Background.EMPTY);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays the greetings to users.
     */
    public void greetUser() {
        String introAndReminder = duke.getResponse("intro");
        addToDialogContainer(
                DialogBox.getDukeDialog(introAndReminder, dukeImage)
        );
    }

    /**
     * Adds the DialogBoxes to the container.
     *
     * @param nodes The Dialog Boxes.
     */
    @SafeVarargs
    private void addToDialogContainer(Node... nodes) {
        dialogContainer.getChildren().addAll(nodes);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing bot's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits when the input is 'bye'.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = duke.getResponse(input);
        addToDialogContainer(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (userInput.getText().equals("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }
}
