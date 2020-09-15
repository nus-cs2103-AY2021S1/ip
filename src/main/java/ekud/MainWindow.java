package ekud;

import java.util.Locale;
import java.util.ResourceBundle;

import ekud.utils.ui.DialogBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author AdithyaNarayan-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart1.html with minor modifications

/**
 * Controller for ekud.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ImageView sendImage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private ResourceBundle strings;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private final Image sendLogo = new Image(this.getClass().getResourceAsStream("/images/sendLogo.png"));

    /**
     * Initialize a dependencies of the main window.
     */
    @FXML
    public void initialize() {
        strings = ResourceBundle.getBundle("StringsBundle", Locale.ENGLISH);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendImage.setImage(sendLogo);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(strings.getString("output.greeting"), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing ekud.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals(strings.getString("command.bye"))) {
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
