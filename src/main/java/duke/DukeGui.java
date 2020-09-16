package duke;

import java.util.concurrent.CompletableFuture;

import duke.gui.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI class handling the graphical aspects of the Duke chatbot.
 */
public class DukeGui extends Application implements Ui {

    // JavaFX pieces
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Images used for the user and the
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Duke duke;
    private boolean isUpdated;
    private String enteredMessage;

    /**
     * Starts the Duke GUI application.
     *
     * @param stage stage for the gui application.
     */
    @Override
    public void start(Stage stage) {

        //@@author Jeffry Lum
        //Reused from https://se-education.org/guides/tutorials/javaFxPart2.html with minor modifications
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //@@author

        duke = new Duke(this);
        CompletableFuture.runAsync(() -> duke.run());
    }

    /**
     * Displays the opening message to the user.
     */
    @Override
    public void startup() {
        Platform.runLater(() ->
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.OPENING_MESSAGE, dukeImage)));
    }

    /**
     * Handles the user input upon receiving it.
     * The method registers that the use has responded and updates the GUI with the user's response.
     */
    private void handleUserInput() {
        String text = userInput.getText();
        userInput.clear();
        if (text.isBlank()) {
            return;
        }

        registerResponse(text);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(text, userImage));
    }

    /**
     * Displays the duke's message to the user.
     *
     * @param message message to be sent.
     */
    public void outputMessage(String message) {
        Platform.runLater(() -> dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage)));
    }

    /** Used to notify the sleeping thread in getInput() to obtain user response asynchronously */
    private synchronized void registerResponse(String enteredMessage) {
        this.enteredMessage = enteredMessage;
        isUpdated = true;
        notifyAll();
    }

    /**
     * Returns the user's input by waiting for the user to provide an update.
     *
     * @return string of the user input
     */
    public synchronized String getInput() {
        isUpdated = false;
        try {
            wait();
        } catch (InterruptedException e) {
            if (!isUpdated) {
                return null;
            }
        }
        return enteredMessage;
    }

    /**
     * Exits the app.
     */
    public void exit() {
        Platform.exit();
    }


}
