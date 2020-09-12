package duke;

import duke.dukehelper.uiparts.DialogBox;
import duke.dukehelper.uiparts.Statistics;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final static String USER_AVATAR = "/images/dnh.jpg";
    private final static String DUKE_AVATAR = "/images/duke.jpg";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;


    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_AVATAR));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_AVATAR));

    @FXML
    public void initialize(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.init(),
                dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if(response == "GET_CHART") {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input,userImage),
                    DialogBox.getStatDialog(new Statistics(), dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input,userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
