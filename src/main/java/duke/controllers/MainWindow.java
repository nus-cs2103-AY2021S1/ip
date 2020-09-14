package duke.controllers;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import duke.Duke;
import duke.Parser;
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
    private static final String UG_WEBSITE = "https://mgiang2015.github.io/ip/";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    @FXML
    private Button helpButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Dave.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Hal9000.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        String response = duke.getResponse(input); // will always be in Duke. Replacing with legit logic later.
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(input, userImage),
                DialogBox.getDukeDialogBox(response, dukeImage)
        );
        if (Parser.isBye(input)) {
            duke.saveCurrentTasks();
        }

        userInput.clear();
    }

    /**
     * Creates Duke welcome message dialog box.
     */
    @FXML
    public void welcomeUser() {
        String dukeLogo = duke.getLogo();
        String dukeWelcome = duke.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialogBox(dukeLogo, dukeImage),
                DialogBox.getDukeDialogBox(dukeWelcome, dukeImage)
        );
    }

    /**
     * Brings User to User Guide website
     */
    @FXML
    public void goToUserGuide() {
        try {
            URI uri= new URI(UG_WEBSITE);
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
