import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        DialogBox userDialog = DialogBox.getUserDialog(userInput.getText(), userImage);
        userDialog.setBorder(new Border(new BorderStroke(Color.ORANGERED,
                                                         BorderStrokeStyle.SOLID,
                                                         new CornerRadii(20.0),
                                                         BorderWidths.DEFAULT)));
        DialogBox dukeDialog = DialogBox.getDukeDialog(duke.getResponse(userInput.getText()), dukeImage);
        dukeDialog.setBorder(new Border(new BorderStroke(Color.ALICEBLUE,
                                                         BorderStrokeStyle.SOLID,
                                                         new CornerRadii(20.0),
                                                         BorderWidths.DEFAULT)));
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );


        userInput.clear();
    }
}