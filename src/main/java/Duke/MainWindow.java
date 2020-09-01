package Duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for Duke.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    public VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private TextField display;
    public boolean isExit = false;
    private Duke duke;
    private boolean intro = true;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Stage stage;

    /**
     * Initializes
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * also ensures that the getDukeDialog prints hello when opened
     * @param d assigns duke value of d
     * @param stage assigns stage value of stage
     */
    public void setDuke(Duke d, Stage stage) {
        duke = d;
        this.stage = stage;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello", dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if(duke.isExit()){
            stage.close();
        }
        String input = userInput.getText(); //input can be multiple input separated by ',' sign
        String[] inputs = input.split(", ");
        String response = duke.getResponse(inputs);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
        userInput.clear();
    }
}
