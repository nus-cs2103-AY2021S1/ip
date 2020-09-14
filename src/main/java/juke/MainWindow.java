package juke;

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

    private Juke juke;

    private Image man = new Image(this.getClass().getResourceAsStream("/images/Man.jpg"));
    private Image woman = new Image(this.getClass().getResourceAsStream("/images/Woman.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Juke d) {
        juke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Juke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = juke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, man),
                DialogBox.getDukeDialog(response, woman)
        );
        userInput.clear();
    }
}
