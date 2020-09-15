import fei.Fei;
import fei.tool.Ui;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Fei fei;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setFei(Fei fei) {
        this.fei = fei;
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(Ui.greeting(), bot)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String botText = fei.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getBotDialog(botText, bot)
        );
        userInput.clear();
        if (fei.isExit()) {
            PauseTransition delay = new PauseTransition((Duration.seconds(0.5)));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
