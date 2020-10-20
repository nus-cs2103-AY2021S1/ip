import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    @FXML
    private Button sendButton;

    private Cait cait;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image caitImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        DialogBox greeting = DialogBox.getCaitDialog(MainWindow.showGreeting(), caitImage);
        dialogContainer.getChildren().add(greeting);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCait(Cait c) {
        cait = c;
    }


    protected static String showGreeting() {
        String logo = "_________         _____     .______________\n"
                + "\\_     ___   \\    /   _    \\   |     \\__      ___/\n"
                + "/      \\     \\/  /    /_\\    \\ |      |   |      |   \n"
                + "\\        \\____/        |       \\      |   |      |   \n"
                + "  \\______    /\\____ |__    / ___|   |____|     \n"
                + "             \\/               \\/               \n";
        String result = ("Hi! I'm\n" + logo + "\n");
        result += ("What can I help you with?\n");
        result += "(Type 'help' for a list of commands!)";
        return result;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cait's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.toLowerCase().equals("bye")) {
            String response = cait.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getCaitDialog(response, caitImage)
            );
            userInput.clear();
            Platform.exit();
        }
        String response = cait.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCaitDialog(response, caitImage)
        );
        userInput.clear();
    }

}
