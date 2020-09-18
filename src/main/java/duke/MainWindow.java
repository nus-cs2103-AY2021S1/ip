package duke;

import duke.command.CommandType;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImageMain = new Image(this.getClass().getResourceAsStream("/images/baronBotMain.png"));
    private Image dukeImagePending = new Image(this.getClass().getResourceAsStream("/images/baronBotPending.png"));
    private Image dukeImageTick = new Image(this.getClass().getResourceAsStream("/images/baronBotTick.png"));
    private Image dukeImageAlert = new Image(this.getClass().getResourceAsStream("/images/baronBotAlert.png"));
    private Image dukeImageCross = new Image(this.getClass().getResourceAsStream("/images/baronBotCross.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private Image getResponseImage(ImageType responseImageType) {
        Image returnImage = dukeImageMain;
        switch (responseImageType) {
        case PENDING:
            returnImage = dukeImagePending;
            break;
        case TICK:
            returnImage = dukeImageTick;
            break;
        case ALERT:
            returnImage = dukeImageAlert;
            break;
        case CROSS:
            returnImage = dukeImageCross;
            break;
        default:
        }
        return returnImage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DukeResponse dukeResponse = duke.getResponse(input);
        String responseText = dukeResponse.getResponseText();
        ImageType responseImageType = dukeResponse.getImageType();
        Image responseImage = this.getResponseImage(responseImageType);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(responseText, responseImage);
        dialogContainer.getChildren().addAll(
                userDialog, dukeDialog);
        userInput.clear();
        if (dukeResponse.isExitCommand()) {
            Platform.exit();
        }
    }
}
