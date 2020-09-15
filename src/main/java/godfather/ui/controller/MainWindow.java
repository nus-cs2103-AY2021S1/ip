package godfather.ui.controller;

import godfather.VitoCorleone;
import godfather.enums.Message;
import godfather.ui.DialogBox;
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
    @FXML // allows FXML to access these private attributes
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private VitoCorleone vitoCorleone;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/marilyn3.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/godfather3.png"));
    /**
     * Initialize Duke by printing welcome greeting
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Message.GREETING.getMsg(), dukeImage));
    }
    public void setDuke(VitoCorleone d) {
        this.vitoCorleone = d;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.vitoCorleone.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                                             DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
    /*
    Gif cropped from: http://gifgifs.com/crop/
    gif taken from: http://christianblanchard.com/hs-cinemagraph/
     */
}
