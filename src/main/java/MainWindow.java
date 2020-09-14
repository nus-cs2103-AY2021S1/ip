import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Duke duke = new Duke();
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes methods when launching GUI
     */
    @FXML
    public void initialize() {
        Region spacer = new Region();
        spacer.setMinHeight(20);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String text = ui.startUp(duke.getTaskList(), duke.getStorage());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(text, dukeImage), spacer);
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
        String input = userInput.getText();
        String response = duke.getResponse(input);
        Region spacer = new Region();
        spacer.setMinHeight(20);
        Region spacer2 = new Region();
        spacer2.setMinHeight(20);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                spacer,
                DialogBox.getDukeDialog(response, dukeImage),
                spacer2
        );

        if (Parser.checkBye(input)) {
            Node source = userInput.getParent();
            Stage stage = (Stage) source.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(0));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
        userInput.clear();
    }
}
