package duke;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the window when Duke starts up.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String logo = "      ▄▀▄     ▄▀▄\n" +
                    "     ▄█░░▀▀▀▀▀░░█▄\n" +
                    " ▄▄  █░░░░░░░░░░░█  ▄▄\n" +
                    "█▄▄█ █░░▀░░┬░░▀░░█ █▄▄█";

        logo += "\n█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\uD83C\uDF3C\n" +
                "█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█\uD83C\uDF3C\n" +
                "█░░║║║╠─║─║─║║║║║╠─░░█\uD83C\uDF3C\n" +
                "█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█\uD83C\uDF3C\n" +
                "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█\uD83C\uDF3C";

        String welcome = logo + "\nHello! I'm Mushy \uD83C\uDF1F\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
