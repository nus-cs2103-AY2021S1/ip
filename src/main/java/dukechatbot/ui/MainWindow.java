package dukechatbot.ui;

import java.util.Arrays;
import java.util.Objects;

import dukechatbot.duke.Duke;
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

    private Duke duke;
    
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String initMessage = Duke.getGreetingMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(initMessage, dukeImage, false));
        assert(dialogContainer.getChildren().size() == 1);
    }

    public void setDuke(Duke d) {
        assert(!Objects.isNull(d));
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
        boolean isErrorResponse = false;
        if (this.isErrorResponse(response)) {
            isErrorResponse = true;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, isErrorResponse)
        );
        userInput.clear();
    }
    
    private boolean isErrorResponse(String response) {
        String[] components = response.split("\\r?\\n");
        return components[1].split("\\s+")[1].equals("\u2639");
    }
}
