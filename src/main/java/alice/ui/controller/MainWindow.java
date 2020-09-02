package alice.ui.controller;

import java.io.IOException;

import alice.Alice;
import alice.command.result.CommandResult;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {
    private static final Font SYSTEM_FONT = new Font(12);

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Stage primaryStage;
    private final Alice alice;

    /**
     * Creates the display for the Alice GUI, which contains the main chat interface.
     *
     * @param primaryStage the Stage to place the main layout.
     * @param alice        the Alice program that handles the logic.
     * @throws IOException if an error occur during FXML loading.
     */
    public MainWindow(Stage primaryStage, Alice alice) throws IOException {
        this.alice = alice;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(primaryStage);

        fxmlLoader.load();
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/icon.png")));
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Show the stage to the user.
     */
    public void show() {
        primaryStage.show();
    }

    /**
     * Welcomes the user and display the load status of the program's initialisation.
     * This method is called immediately after the class is loaded and constructed.
     */
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displaySystemMessage(String.format("<%s>", alice.getLoadStatus()));
        welcomeUser();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Alice's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));

        CommandResult result = alice.processCommand(input);

        DialogBox responseBubble;
        if (result.isCommandFailure()) {
            responseBubble = respondToUser("ERROR: " + result.getMessage());
        } else {
            responseBubble = respondToUser(result.getMessage());
        }

        if (result.shouldExit()) {
            exitAlice();
        }

        responseBubble.addSaveIndicator(result.getSaveStatus());

        userInput.clear();
    }

    private DialogBox respondToUser(String response) {
        DialogBox db = DialogBox.getAliceDialog(response);
        dialogContainer.getChildren().add(db);
        return db;
    }

    private void exitAlice() {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> primaryStage.close());
        delay.play();
    }

    private void welcomeUser() {
        respondToUser("Hello! I'm Alice\n"
                + "How can I help you today?");
    }

    private void displaySystemMessage(String message) {
        Label sysMessage = new Label(message);
        sysMessage.setFont(SYSTEM_FONT);
        sysMessage.setWrapText(true);
        sysMessage.setTextFill(Paint.valueOf("blue"));

        dialogContainer.getChildren().add(sysMessage);
    }
}
