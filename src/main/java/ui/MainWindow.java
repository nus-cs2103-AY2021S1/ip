package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Duke;
import model.ResponseResult;

/**
 * Controller for ui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    //private Image userImage = null;
    //private Image dukeImage = null;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DukeUserPxl.PNG"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeBunnyPxl.PNG"));
    private Image dukeImageError = new Image(this.getClass().getResourceAsStream("/images/DukeBunnyErrorPxl.PNG"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        //Welcome Message
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.initDuke(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        ResponseResult response = duke.getResponse(input);
        if (response.isError()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response.getMsg(), dukeImageError)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response.getMsg(), dukeImage)
            );
        }
        userInput.clear();
    }
}
