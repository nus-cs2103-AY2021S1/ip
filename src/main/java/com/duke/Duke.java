package com.duke;

import com.duke.exceptions.DukeException;
import com.duke.gui.controllers.DialogBox;
import com.duke.storage.Storage;
import com.duke.tasklist.TaskList;
import com.duke.ui.Ui;

import javafx.application.Application;
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
 * Entry point to application.
 * Initializes all components needed to run program.
 */

public class Duke extends Application {
    private static String FILE_PATH = "src/main/data/input.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Duke Default Constructor.
     */
    public Duke() {
        this.storage = new Storage(FILE_PATH);
        try {
            this.tasks = new TaskList(storage.load());
            this.ui = new Ui(tasks);
            ui.initialize();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
            this.ui = new Ui(tasks);
        }
    }
    /**
     * Duke Default Constructor. Includes filepath to persistent storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step2. Format window to look as expected
        stage.setTitle("Duke Application");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String res = this.ui.listen(input);
        return res;
    }

}
