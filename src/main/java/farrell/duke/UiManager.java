package main.java.farrell.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

/**
 * Encapsulates behavior for interacting with the user.
 * This class uses System.in to receive inputs from the user and System.out to display its output.
 */
public class UiManager extends Application {
    private Duke duke;
    private VBox dialogContainer;
    private TextField userInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialising Components
        dialogContainer = new VBox();
        userInput = new TextField();

        ScrollPane scrollPane = new ScrollPane();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();

        //Formatting and Style
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400, 600);

        Scene scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(600);

        // Setup event handlers
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput.getText());
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput.getText());
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        primaryStage.show();

        // Initialise program
        try {
            duke = new Duke(this);
        } catch (DukeException exception) {
            sendDukeMessage(exception.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
        super.stop();
    }

    private void handleUserInput(String input) {
        duke.run(input);

        userInput.clear();
    }

    /**
     * Returns the string for the welcome message.
     */
    public void displayStartMessage() {
        sendDukeMessage("Hello, I'm a banana.\nWhat can I do for you?");
    }

    /**
     * Displays text inside a formatted window.
     *
     * @param text The text to display.
     */
    public void sendDukeMessage(String text) {
        dialogContainer.getChildren().add(new DukeDialog(text));
    }

    public void sendUserMessage(String text) {
        dialogContainer.getChildren().add(new UserDialog(text));
    }


}
