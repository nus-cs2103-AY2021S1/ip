package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    public void setDuke(Duke d) {
        duke = d;
        ui = new Ui();
    }

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
