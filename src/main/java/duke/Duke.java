package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.command.Command;
import duke.common.DukeException;
import duke.common.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/fileInfo.txt");
        Ui.displayWelcome();
        try {
            ArrayList<Task> currenttasks = Storage.readFile();
            tasks = new TaskList(currenttasks);
            if (!currenttasks.isEmpty()) {
                Ui.displayTasks(tasks);
            } else {
                Ui.display("No current list available. Start by adding a task!");
            }
        } catch (FileNotFoundException e) {
            tasks = new TaskList(new ArrayList<Task>());
        }
    }
    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param filePath directory of where the storage file is located in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        Ui.displayWelcome();
        try {
            ArrayList<Task> currenttasks = Storage.readFile();
            tasks = new TaskList(currenttasks);
            if (!currenttasks.isEmpty()) {
                Ui.displayTasks(tasks);
            } else {
                Ui.display("No current list available. Start by adding a task!");
            }
        } catch (FileNotFoundException e) {
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                Ui.display(e.getMessage());
            }
        }
    }

    //    public static void main(String[] args) {
//        new Duke("data/fileInfo.txt").run();
//    }

    /**
     * Parses user input and executes command accordingly.
     */
    public void parseInput(String input) {
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
            } catch (DukeException | IOException e) {
                Ui.display(e.getMessage());
            }
        }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
//        parseInput(input);
        return "NIL";
    }

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }
//
////    /**
////     * Iteration 1:
////     * Creates a label with the specified text and adds it to the dialog container.
////     * @param text String containing text to add
////     * @return a label with the specified text that has word wrap enabled.
////     */
////    private Label getDialogLabel(String text) {
////        // You will need to import `javafx.scene.control.Label`.
////        Label textToAdd = new Label(text);
////        textToAdd.setWrapText(true);
////
////        return textToAdd;
////    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }


}
