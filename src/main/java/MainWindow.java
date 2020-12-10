import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nobita.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/doraemon.png"));

    /**
     * Initialises the stage with a welcome message.
     */
    @FXML
    public void initialize() {
        assert(userImage != null);
        assert(dukeImage != null);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.showWelcomeMessage(), dukeImage));
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
        String response = duke.getResponse(input);
        if (response.equals(Ui.showByeMessage())) {
            HBox exitButton = setExitButton();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage),
                    exitButton);
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }

    private HBox setExitButton() {
        //Solution below adapted from https://stackoverflow.com/questions/39214586/how-to-align-a-button-right-in-javafx
        HBox hbox = new HBox();
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button exitButton = new Button("Exit");
        exitButton.setPadding(new Insets(10, 10, 10, 10));
        exitButton.setOnAction(e -> closeStage());
        hbox.getChildren().addAll(spacer, exitButton);
        return hbox;
    }

    @FXML
    void closeStage() {
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}
