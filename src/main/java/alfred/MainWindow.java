package alfred;

import java.util.concurrent.CompletableFuture;

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

    private Alfred alfred;

    // Image retrieved from https://www.pngfind.com/mpng/hwwTTi_free-png-download-lego-batman-movie-clipart-png/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    // Image retrieved from https://www.pngfind.com/mpng/TRwRibh_alfred-lego-batman-movie-lego-batman-alfred-png/
    private Image alfredImage = new Image(this.getClass().getResourceAsStream("/images/alfred.png"));

    /**
     * Initializes the user interface and displays the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getAlfredDialog(UI.greet(), alfredImage)
        );
    }

    public void setAlfred(Alfred d) {
        alfred = d;

        if (!alfred.loadedFromStorage()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getAlfredDialog("Unfortunately, I was unable to load from storage", alfredImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alfred's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alfred.getResponse(input);
        assert input != null : "User input cannot be null";
        assert response != null : "User response cannot be null";
        assert userImage != null : "User image in MainWindow cannot be null";
        assert alfredImage != null : "Alfred image in MainWindow cannot be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlfredDialog(response, alfredImage)
        );
        userInput.clear();
        if (response.equals("Goodbye! The application will close shortly...")) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            cf.thenRun(Platform::exit).thenRun(() -> System.exit(0));
        }
    }
}
