import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sparrow.Sparrow;
import sparrow.commands.ExitCommand;

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

    private Sparrow sparrow;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/WillTurner.png"));
    private final Image sparrowImage = new Image(this.getClass().getResourceAsStream("/images/JackSparrow.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSparrow(Sparrow s) {
        sparrow = s;
    }

    /**
     * Sends welcome message to user in Gui window.
     */
    public void welcome() {
        String welcomeText = "Welcome to Sparrow!\n What can I do for ye?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, sparrowImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sparrow's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sparrow.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, sparrowImage)
        );
        if (response.equals(ExitCommand.MESSAGE_EXIT)) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            //@@author jonfoocy-reused
            //Reused from https://stackoverflow.com/a/27334614/ with minor modifications
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished((event) -> stage.close());
            delay.play();
            //@@author
        }
        userInput.clear();
    }
}
