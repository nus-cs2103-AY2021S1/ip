package duke.ui;

import java.util.concurrent.CompletableFuture;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    // Image retrieved from https://line.fandom.com/wiki/Cony
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cony.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up Duke.
     *
     * @param duke
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        duke.setUi(dialogContainer);
        duke.greet();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (duke.getResponse(input)) {
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            completableFuture.thenRun(() -> Platform.exit());
        }
        userInput.clear();
    }
}
