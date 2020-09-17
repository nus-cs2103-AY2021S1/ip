import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

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
    private TextFlow txtF;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image mascotImage = new Image(this.getClass().getResourceAsStream("/images/DaMascot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = Ui.welcomeGreetings();
        Text text = new Text(welcomeMessage);
        txtF.getChildren().add(text);
    }

    /**
     * References the same Duke object initialised in Main.
     * @param d is the Duke object initialised by Main.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Shows and reminds user of the upcoming tasks to complete.
     */
    public void showReminder(String reminders) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(reminders, mascotImage)
        );
    }

    /**
     * Closes the window.
     */
    public static void closeWindow() {
        Platform.exit();
    }

    /**
     * Creates dialog box for reminders of tasks on pressing the Reminder button.
     */
    @FXML
    private void handleReminderAction() {
        String reminder = duke.getReminder();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(reminder, mascotImage)
        );
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
