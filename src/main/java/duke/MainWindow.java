package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for Duke.MainWindow. Provides the layout for the other controls.
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
    @FXML
    private TextField display;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Stage stage;

    /**
     * Initializes scrollPane
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Also ensures that the getDukeDialog prints hello when opened
     *
     * @param d assigns duke value of d
     * @param stage assigns stage value of stage
     */
    public void setDuke(Duke d, Stage stage) {
        duke = d;
        this.stage = stage;
        Response hello = new Response("  Hello! I'm Duke\n" + "  What can I do for you?", false);
        //opening message and isException is false as it is not an error
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(hello, dukeImage)); //Introduction given by Duke
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (duke.isExit()) {
            stage.close(); //since bye message is given
        }
        String input = userInput.getText(); //input can be multiple input separated by ',' sign
        String[] inputs = input.split(", ");
        Response[] responses = duke.getResponse(inputs); //response given by duke
        for (int i = 0; i < inputs.length; i++) {
            DialogBox user = DialogBox.getUserDialog(inputs[i], userImage); //DialogBox for user
            DialogBox dukeResponse = DialogBox.getDukeDialog(responses[i], dukeImage); //DialogBox from Duke
            dialogContainer.getChildren().addAll(user, dukeResponse); //Duke input displayed
        }
        userInput.clear();
        HBox buttonsContainer = new HBox();
        buttonsContainer.getStyleClass().add("jfx-decorator-buttons-container");
    }
}
