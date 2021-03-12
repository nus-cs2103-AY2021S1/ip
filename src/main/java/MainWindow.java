import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Image k = new Image(this.getClass().getResourceAsStream("/images/k.png"));
    private Image marco = new Image(this.getClass().getResourceAsStream("/images/marco.png"));
    private static final String LINE_TOP =    "╭⋟──────────────❀• *₊。❀°。─╮\n";
    private static final String LINE_BOTTOM = "╰─────────────────────────⋞╯\n";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Ui().greet(), marco));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Duke testBot = new Duke("data/tasks.txt");
        String formattedInput = LINE_TOP
                + userInput.getText() + '\n' + LINE_BOTTOM;

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(formattedInput, k),
                DialogBox.getDukeDialog(getResponse(userInput.getText(),testBot),marco)
        );
        userInput.clear();
    }

    private String getResponse(String input, Duke testBot) {
        return Parser.respond(input, testBot.tasks, testBot.storage.filePath);
    }
}
