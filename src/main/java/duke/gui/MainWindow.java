package duke.gui;

import duke.Duke;
import duke.logic.UiManager;
import duke.logic.UserInteractionUi;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/gudetamaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/gudetamaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private UserInteractionUi uiManager;

    /**
     * Initialises values required for the MainWindow
     */
    @FXML
    public void initialize() {
        //scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setStyle("-fx-background-image: url('/images/yellowBackground.png'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: repeat; "
                + "-fx-background-size: cover;");
        userInput.setStyle("-fx-background-color: #FFEFD5");
        userInput.setPromptText("Wake GuDukeTama up!");
        sendButton.setStyle("-fx-background-color: #F5E0C2");
        //#FFFCD4
    }

    public void setDuke(Duke d) {
        duke = d;
        uiManager = new UiManager();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        userInput.clear();
        if (duke.isGuiExit()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(uiManager.getDukeOutro(), dukeImage)
            );
            scrollPane.layout();
            scrollPane.setVvalue(1.0);
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        } else {
            DialogBox userBox = DialogBox.getUserDialog(input, userImage);
            DialogBox dukeBox = DialogBox.getDukeDialog(response, dukeImage);
            dialogContainer.getChildren().addAll(
                    userBox, dukeBox
            );
            scrollPane.layout();
            double ratio = (dialogContainer.getHeight() - dukeBox.getHeight())
                    / dialogContainer.getHeight();
            scrollPane.setVvalue(ratio);
        }
    }

    /**
     * Displays Duke introduction on the GUI
     */
    public void introDuke() {
        String intro = uiManager.getDukeIntro();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(intro, dukeImage));
    }
}
