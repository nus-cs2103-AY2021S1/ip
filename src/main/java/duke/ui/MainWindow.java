package duke.ui;

import duke.main.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Class representing the overall shape of the GUI.
 */
public class MainWindow extends AnchorPane {
    // @@author Jeffry Lum-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    // Image taken from https://utulsa.edu/sexual-violence-prevention-education/generic-avatar/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    // Image taken from Sodacan: https://commons.wikimedia.org/wiki/File:Imperial_Crown_(Heraldry).svg
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        greet();
    }
    // @@author

    private void greet() {
        dialogContainer.getChildren().add(DialogBox.getDukeGreeting(dukeImage));
    }

    // @@author Jeffry Lum-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modification
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
    // @@author
}
