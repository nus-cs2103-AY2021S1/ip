import gel.Gel;
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

    private Gel gel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jake.png"));
    private Image gelImage = new Image(this.getClass().getResourceAsStream("/images/finn.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void showWelcomeMsg() {
        dialogContainer.getChildren().addAll(
                DialogBox.getGelDialog(gel.getWelcomeMsg(), gelImage)
        );
    }

    public void setGel(Gel g) {
        gel = g;
        showWelcomeMsg();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = gel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGelDialog(response, gelImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}
