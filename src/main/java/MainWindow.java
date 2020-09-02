import command.Command;
import exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parserstorageui.Parser;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mrbean.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/troll.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        /*dialogContainer.getChildren().add(new Label("Hello I'm Verzachtend \n"
        + "What can I do for you?\n"
            + "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE"));*/
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello I'm Verzachtend \n"
            + "What can I do for you?\n"
            + "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE" , dukeImage));

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

        String fullCommand = userInput.getText();
        try {
            Command c = Parser.parse(fullCommand);
            String dukeText = duke.getResponse(c);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(fullCommand, userImage),
                DialogBox.getDukeDialog(dukeText , dukeImage)
            );
            userInput.clear();
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(fullCommand, userImage),
                DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
            userInput.clear();
        }
    }
}