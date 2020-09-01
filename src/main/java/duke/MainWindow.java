package duke;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Chicken.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Chicken.png"));

    public static String greeting() {
        String logo = "         ███▄ ▄███▓  ██▓     ▄████▄    ██░   ██ "
                + "\n         ▓██▀█▀ ██▒ ▓██▒ ▒██▀ ██   ▓██░  ██▒"
                + "\n         ▓██       ▓██░ ▒██▒ ▓█        ▄   ▒██▀▀██░"
                + "\n         ▒██       ▒██   ░██░  ▓▓▄ ▄██▒ ██    ░██ "
                + "\n         ▒██▒     ░██▒ ░██░▒  ▓███▀░██▒ ░██▓";

        String greet = "\n_____________________________________________________"
                + "\n Hello! I'm Mich"
                + "\n What can I do for you?"
                + "\n_____________________________________________________\n";
        String str = logo + greet;
        return str;
    }

    @FXML
    public void initialize() {
        DialogBox greeting = DialogBox.getDukeDialog(MainWindow.greeting(), dukeImage);
        dialogContainer.getChildren().add(greeting);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
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