import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDeuk.png"));

    @FXML
    public void initialize() {
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
    private void handleUserInput() {
        String input = userInput.getText() + "  ";
        String response = duke.getResponse(input);
        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialogBox = DialogBox.getDukeDialog(response, dukeImage);

        //This makes it such that the size of the dialog box is always able to hold all of the text
        dukeDialogBox.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialogBox,
                dukeDialogBox
        );
        String goodByeString = String.format(Ui.MESSAGE_TEMPLATE_VERBAL, "Goodbye, hope to see you again!");
        if (response.equals(goodByeString)) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Platform.exit();
                            System.exit(0);
                        }
                    },
                    700
            );
        }
        userInput.clear();
    }
}