package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Duke duke;

    private Image dukeSadge = new Image(this.getClass().getResourceAsStream("/img/peepoSad.png"));
    private Image dukeHappy = new Image(this.getClass().getResourceAsStream("/img/peepoHappy.png"));
    private Image dukeNotes = new Image(this.getClass().getResourceAsStream("/img/peepoNotes.png"));

    /**
     * Initialise.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void loadInitMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                new Label(duke.getWelcome()), new ImageView(dukeHappy))
        );
    }

    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        ResultInfo resultInfo = duke.getResponse(userInput.getText());
        if (resultInfo.isExit()) {
            Platform.exit();
            //Main.getStage().close();
        } else {
            ImageView img = new ImageView(resultInfo.isSuccess() ? dukeNotes : dukeSadge);
            Label dukeText = new Label(resultInfo.getResponse());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText),
                    DialogBox.getDukeDialog(dukeText, img)
            );
            userInput.clear();
        }
    }
}
