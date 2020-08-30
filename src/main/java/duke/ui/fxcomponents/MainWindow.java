package duke.ui.fxcomponents;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Duke;
import duke.ui.GuiHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainWindow component of the GUI. Contains additional logic to
 * format and display messages.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollArea;
    @FXML
    private VBox outputContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private GuiHelper guiHelper;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/naturo.png"));

    @FXML
    public void initialize() {
        scrollArea.vvalueProperty().bind(outputContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        this.guiHelper.setUserInput(input);
        this.userMessage(input);
        this.duke.nextIteration();
        if (!this.guiHelper.isActive()) {
            this.closeWindow();
        }
        this.guiHelper.consumeCommandOutput().ifPresent((messages) -> {
            for (String message : messages) {
                this.dukeMessage(message);
            }
        });
        userInput.clear();
    }

    /**
     * 'constructor' for this component.
     * Initializes the component.
     */
    public void setup() {
        this.guiHelper = new GuiHelper();
        this.duke = new Duke(guiHelper);
        this.sendWelcomeMessage();
    }

    private void sendWelcomeMessage() {
        final String welcomeMessage = "hello sir\nits me your assistant sir\nhow may i be of service sir";
        this.dukeMessage(welcomeMessage);
    }

    private void closeWindow() {
        // Somehow this.getScene... doesn't work but sendButton.getScene... does
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

    private void sendMessage(String message, boolean isFromDuke) {
        this.splitMessage(message).stream().forEach((line) -> {
            if (isFromDuke) {
                outputContainer.getChildren().add(DialogBox.createDukeDialog(line, dukeImage));
            } else {
                outputContainer.getChildren().add(DialogBox.createUserDialog(line, userImage));
            }
        });
    }

    private void dukeMessage(String message) {
        this.sendMessage(message, true);
    }

    private void userMessage(String message) {
        this.sendMessage(message, false);
    }

    /**
     * Splits long messages up such that they can be displayed in
     * the DialogBox properly.
     * Wraps each line at 40 characters, and groups every 4 lines
     * together in an ArrayList.
     * @param message
     * @return
     */
    private ArrayList<String> splitMessage(String message) {
        final int numLines = 4;
        String[] lines = message.split("\\n");
        ArrayList<String> allLines = new ArrayList<>();
        for (String line : lines) {
            // split the string every 40 chars, code from:
            // https://stackoverflow.com/questions/2297347/splitting-a-string-at-every-n-th-character
            allLines.addAll(Arrays.asList(line.split("(?<=\\G.{40})")));
        }

        ArrayList<String> messageChunks = new ArrayList<>();
        for (int i = 0; i < allLines.size(); i += numLines) {
            String chunk = "";
            for (int j = i; j < allLines.size() && j < i + numLines; j++) {
                if (j != i) {
                    chunk += "\n";
                }
                chunk += allLines.get(j);
            }
            messageChunks.add(chunk);
        }
        return messageChunks;
    }
}
