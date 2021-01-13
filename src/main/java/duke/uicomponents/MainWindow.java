package duke.uicomponents;

import duke.Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

    /* JAVAFX Main GUI Elements */
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    /* ------------------------- */

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pipicon.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/https _specials-images.forbesimg.com_imageserve_5c76b7d331358e35dd2773a9_0x0.jpg background=000000&cropX1=0&cropX2=4401&cropY1=0&cropY2=4401.jpg"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    }
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private void handleExit() {
        Platform.exit();
        duke.exit();
    }
}
