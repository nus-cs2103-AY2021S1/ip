import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**@@author SE-EDU student project guide, a sub-project of the se-education.org.
 * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
 *
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/LiYu.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/PowerConvert.png"));
    private Image mascotImage = new Image(this.getClass().getResourceAsStream("/images/LangYa.png"));

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
     * Initialises the window design when program is first started.
     */
    @FXML
    public void initialize() {
        //Scroll pane
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        //Welcome text
        String welcomeMessage = Ui.welcomeGreetings();
        Text text = new Text(welcomeMessage);
        Background dialogBackground = new Background(
                new BackgroundFill(Color.rgb(3, 252, 248, 0.2),
                        new CornerRadii(5.0), new Insets(-3.0)));
        txtF.setBackground(dialogBackground);
        txtF.getChildren().add(text);
    }

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
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
     * Prints GUI reminder message whenever the "Commands" button is pressed.
     */
    @FXML
    private void handleCommandAction() {
        String commandList = Ui.getCommandList();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(commandList, mascotImage)
        );
    }

    /**
     * Creates dialog box for reminders of tasks on pressing the "My Reminder" button.
     */
    @FXML
    private void handleReminderAction() {
        String reminder = duke.getReminder();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(reminder, mascotImage)
        );
    }

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
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
