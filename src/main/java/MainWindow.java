// Solution below are adapted from https://se-education.org/guides/tutorials/javaFx.html
import duke.component.Ui;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
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
    public static final String STYLE_BORDER = "-fx-border-radius: 20; -fx-background-radius: 20";
    public static final String TEXT_LIGHT_COLOR = "-fx-text-fill: aliceblue;";
    public static final String NORMAL_BUTTON_COLOR = " -fx-background-color: darkslategrey; ";
    public static final String HOVER_BUTTON_COLOR = "-fx-background-color: #107c85;";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private App app;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setStyle(STYLE_BORDER);
        sendButton.setStyle(TEXT_LIGHT_COLOR + NORMAL_BUTTON_COLOR
                + STYLE_BORDER);
        sendButton.setOnMouseEntered(e -> {
            sendButton.setStyle(TEXT_LIGHT_COLOR + HOVER_BUTTON_COLOR
                    + STYLE_BORDER);
            sendButton.setCursor(Cursor.HAND);
        });
        sendButton.setOnMouseExited(e -> {
            sendButton.setStyle(TEXT_LIGHT_COLOR + NORMAL_BUTTON_COLOR
                    + STYLE_BORDER);
            sendButton.setCursor(Cursor.DEFAULT);
        });
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(Ui.GREETING), new ImageView(dukeImage))
        );
    }

    public void setDuke(App d) {
        app = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Label response = app.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), new ImageView(userImage)),
                DialogBox.getDukeDialog(response, new ImageView(dukeImage))
        );
        userInput.clear();
    }
}
