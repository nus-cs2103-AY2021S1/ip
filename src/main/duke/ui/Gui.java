package duke.ui;

import duke.command.Command;
import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.exception.DukeIoException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.ui.component.DialogBox;
import duke.ui.component.ScrollDialoguePane;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui extends Application {

    // Images used for displaying
    private static final Image IMG_USER = new Image("/images/user.jpg");
    private static final Image IMG_DUKE = new Image("/images/duke.png");
    // Styles
    private static final String CSS_LABEL_PADDING_BG = "-fx-padding: 5px;\n";
    // File path for storage
    private static final String PATH = "data.txt";
    // Components for the GUI.
    private final Storage storage;
    private final TextField userInput;
    private final Button sendButton;
    private final ScrollDialoguePane scrollDialoguePane;
    private final PauseTransition exitPause;
    private final AnchorPane layout;

    /**
     * Constructor for the GUI.
     *
     * @throws DukeIoException if an error occurs when creating the Storage object
     */
    public Gui() throws DukeIoException {
        // initialise each component
        storage = new Storage(PATH);
        userInput = new TextField();
        sendButton = new Button("Send");
        scrollDialoguePane = new ScrollDialoguePane();
        exitPause = new PauseTransition(Duration.seconds(1));
        layout = new AnchorPane();

        // Add settings for the components
        userInput.setPrefWidth(330);
        scrollDialoguePane.setPrefSize(385, 535);
        layout.getChildren().addAll(userInput, sendButton, scrollDialoguePane);
        layout.setPrefSize(390, 570);
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(23, 36, 33),
                new CornerRadii(0.0), new Insets(0.0))));
        AnchorPane.setTopAnchor(scrollDialoguePane, 5.0);
        AnchorPane.setLeftAnchor(scrollDialoguePane, 3.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);
        AnchorPane.setLeftAnchor(userInput, 5.0);


        // Send greeting message
        DialogBox greetingDialog = dukeDialog("Hello. You can use \"help\" to see the help menu.");
        scrollDialoguePane.addDialog(greetingDialog);
    }

    @Override
    public void start(Stage stage) {
        // Stage initialisation
        Scene scene = new Scene(layout);

        stage.setTitle("Duke");
        stage.setResizable(false);


        stage.setScene(scene);
        stage.show();

        exitPause.setOnFinished(event -> stage.close());

        // Handle user input events
        sendButton.setOnMouseClicked(event -> {
            if (handleUserInput()) {
                exitPause.playFromStart();
            }
            userInput.clear();
        });

        userInput.setOnAction(event -> {
            if (handleUserInput()) {
                exitPause.playFromStart();
            }
            userInput.clear();
        });
    }

    /**
     * Method to format text into a Label according to the styles given.
     *
     * @param text the text to be formatted.
     * @return a Label object with formatting applied.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setStyle(CSS_LABEL_PADDING_BG);
        textToAdd.setTextFill(Color.web("#FFFFFF")); // white text
        return textToAdd;
    }

    /**
     * Executes commands that are sent to Duke and replies with the appropriate message returned by the command.
     *
     * @return if the command would have the program exit.
     */
    private boolean handleUserInput() {
        String input = userInput.getText();
        boolean shouldExit = false;
        try {
            Command command = CommandParser.parseCommand(input);

            DialogBox user = userDialog(input);
            scrollDialoguePane.addDialog(user);

            String dukeMessage = command.execute(storage);
            DialogBox duke = dukeDialog(dukeMessage);
            scrollDialoguePane.addDialog(duke);

            shouldExit = command.shouldExit();
        } catch (DukeArgumentException | DukeExecutionException de) {
            DialogBox user = userDialog(input);
            scrollDialoguePane.addDialog(user);

            DialogBox duke = dukeDialog(de.getMessage());
            scrollDialoguePane.addDialog(duke);
        }
        return shouldExit;
    }

    /**
     * Method to create a DialogBox from the Duke.
     *
     * @param text the message to be sent.
     * @return a DialogBox that can be inserted into the ScrollDialoguePane
     */
    private DialogBox dukeDialog(String text) {
        Label dialogLabel = getDialogLabel(text);
        ImageView dukeImage = new ImageView(IMG_DUKE);
        return DialogBox.dukeDialog(dialogLabel, dukeImage);
    }

    /**
     * Method to create a DialogBox from the User.
     *
     * @param text the message to be sent.
     * @return a DialogBox that can be inserted into the ScrollDialoguePane
     */
    private DialogBox userDialog(String text) {
        Label dialogLabel = getDialogLabel(text);
        ImageView userImage = new ImageView(IMG_USER);
        return DialogBox.userDialog(dialogLabel, userImage);
    }
}
