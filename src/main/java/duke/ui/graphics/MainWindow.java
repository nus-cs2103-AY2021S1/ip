package duke.ui.graphics;

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

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private GuiHelper guiHelper;
    private Duke duke;

    private final Image userImg = new Image(getClass().getResourceAsStream("/images/caocao.gif"));
    private final Image dukeImg = new Image(getClass().getResourceAsStream("/images/trump.gif"));

    /**
     * Substitute constructor for GUI
     */
    public void setup() {
        guiHelper = new GuiHelper();
        duke = new Duke(guiHelper);
        greeting();
    }

    /**
     * Initialise the Mainwindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        guiHelper.setUserInput(input);
        userMessage(input);
        duke.nextIteration();
        if (!guiHelper.isRunning()) {
            closeWindow();
        }
        guiHelper.consumeCommandOutput().ifPresent((message) -> {
            message.forEach(this::dukeMessage);
        });
        userInput.clear();
    }

    private void greeting() {
        final String welcomeMessage = "Hello Friend! I'm Duke, how may I help you!";
        dukeMessage(welcomeMessage);
    }

    private void closeWindow() {
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

    private void userMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message , userImg));
    }

    private void dukeMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImg));
    }

}

