package duke;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    private Duke duke;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/pingu1.jpg"));
    private Image pingu = new Image(this.getClass().getResourceAsStream("/images/pingu2.jpg"));
    private ImageView pingu1 = new ImageView(user);
    private ImageView pingu2 = new ImageView(pingu);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        makeRoundImage(pingu1);
        makeRoundImage(pingu2);

        dialogContainer.getChildren().addAll(new PinguDialogBox("\tNOOT NOOT! Pingu says hi\n \tEnter 'help' to see help page! ", pingu));
    }

    private void makeRoundImage(ImageView imageView) {
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
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = duke.getResponse(input);

            dialogContainer.getChildren().addAll(
                    new UserDialogBox(input, pingu1.getImage()),
                    new PinguDialogBox(response, pingu2.getImage())
            );
            userInput.clear();

        } catch (NoSuchElementException e) {
            this.dialogContainer.getChildren().addAll(
                    new PinguDialogBox("pingu doesn't understand what that means :(", pingu));
            this.userInput.clear();
        }

    }
}