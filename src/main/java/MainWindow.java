import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @version 1.1
 * @since 2020-09-08
 */
public class MainWindow extends AnchorPane {

    private final String EXIT_STRING = "Bye bye!!! See you again next time :)";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/icebear.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/grizz.jpg"));

    /**
     * Initializes a main window for Duke GUI.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hey there! I'm Grizz.\n" +
                                "What can I do for you today?\n", dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        boolean isEnd = response.equals(EXIT_STRING);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (isEnd) {
            Platform.exit();
        }
    }
}