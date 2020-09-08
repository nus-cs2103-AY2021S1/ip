package duke.ui.controller;

import duke.Duke;
import duke.enums.Message;
import duke.ui.DialogBox;
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
    private Duke duke;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/visualise.gif"));
    /**
     * Initialize Duke by printing welcome greeting
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Message.GREETING.getMsg(), dukeImage));
    }
    public void setDuke(Duke d) {
        this.duke = d;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.duke.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                                             DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
    // todo: 1) add greeting upon startup [done]
    //       2) dynamic resizing of DialogBox for vertical height to display long list
    //       3) make it pretty :) try a matrix rain for the background, or just some stupid animation rendered
    /*
    Gif cropped from: http://gifgifs.com/crop/
    gif taken from: https://www.reddit.com/r/Cinemagraphs/comments/8ceghg/reflections/
     */
}
