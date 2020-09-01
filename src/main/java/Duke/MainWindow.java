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
    public Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Stage stage;
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

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
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
        userInput.clear();
        if(duke.isExit()) {
            stage.setOnCloseRequest(event -> {
                System.out.println(3);
                dialogContainer.getChildren().add(DialogBox.getDukeDialog("Bye", dukeImage));
            });
        }
    }
}
