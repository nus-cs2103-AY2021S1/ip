package duke.gui;

import java.util.concurrent.CompletableFuture;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/** Controller for MainWindow. Provides the layout for the other controls. */
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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/wallpaper.png"));

    /** Initializes the new window. */
    @FXML
    public void initialize() {

        // Background
        BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage bg = new BackgroundImage(bgImage,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT, bgSize);
        dialogContainer.setBackground(new Background(bg));

        // Scroll Pane
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Bot Greeting
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
            "Hello! I am Bolot, your personal chat-bot companion.\n\n"
                + "How may I help you?", dukeImage));
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
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // Terminate when "bye"
        if (input.equalsIgnoreCase("bye")) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(500);
                    System.exit(0);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
