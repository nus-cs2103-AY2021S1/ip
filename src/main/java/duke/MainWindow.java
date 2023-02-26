package duke;

import duke.util.DukeException;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.NoSuchElementException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/piggo-cropped.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/doggo-cropped.png"));
    private ImageView userImageView = new ImageView(userImage);
    private ImageView dukeImageView = new ImageView(dukeImage);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        customiseImage(userImageView);
        customiseImage(dukeImageView);
    }


    private void customiseImage(ImageView imageView) {
        Rectangle rect = new Rectangle(475, 475);
        rect.setArcWidth(475);
        rect.setArcHeight(475);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        imageView.setClip(rect);
        WritableImage writableImage = imageView.snapshot(parameters, null);
        imageView.setImage(writableImage);

    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(d.ui.printWelcomeMessage(), dukeImageView.getImage())
        );
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
                DialogBox.getUserDialog(input, userImageView.getImage()),
                DialogBox.getDukeDialog(response, dukeImageView.getImage())
        );
        userInput.clear();
    }
}
