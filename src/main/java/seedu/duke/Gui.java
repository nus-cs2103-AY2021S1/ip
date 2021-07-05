package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Hold the GUI interaction with user.
 * Code Reuse Credit:
 *  * Some parts of the structure of the code of the class is from CS2103 JavaFX tutorial page.
 *  *  Website of the page:
 *  *  https://se-education.org/guides/tutorials/javaFx.html
 */
public class Gui extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Image userProfile = new Image(this.getClass().getResourceAsStream("/image/UserProfile.jpg"));
    private Image dukeProfile = new Image(this.getClass().getResourceAsStream("/image/DukeProfile.jpg"));
    private Duke duke;
    private Ui ui;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Assigns Duke to the GUI.
     * Initializes UI object.
     * @param d Assigned Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        ui = new Ui();
    }

    /**
     * Sets welcome words to GUI.
     */
    public void setWelcomeWords() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.welcomeWord(), dukeProfile),
                DialogBox.getDukeDialog(ui.showHistory(duke.getHistoryList()), dukeProfile)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.run(input);
        assert !response.isEmpty();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userProfile),
                DialogBox.getDukeDialog(response, dukeProfile)
        );
        userInput.clear();
    }
}
