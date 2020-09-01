package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DateParseException;
import duke.exception.DukeException;
import duke.exception.StorageException;
import duke.task.TaskList;
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
 * Duke is the class encapsulating all application processes.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/neko.png"));

    /**
     * Initialises Duke class.
     * @throws DateParseException if Task created from file information cannot be stored in local storage.
     * @throws StorageException if Task date (if any) cannot be parsed into LocalDate object.
     */
    public Duke() throws DateParseException, StorageException {
        this.storage = new Storage();
        this.taskList = TaskList.initialiseTaskList(this.storage);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400, 535);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Launches and runs the application.
     * @param args Standard arguments
     */
    public static void main(String[] args) {
        try {
            Storage storage = new Storage();
            TaskList taskList = TaskList.initialiseTaskList(storage);

            Scanner scanner = new Scanner(System.in);
            Ui.welcomeMessage();
            boolean isExit = false;

            while (!isExit) {
                try {
                    String userCommand = scanner.nextLine();
                    Command parsedCommand = CommandParser.parseCommand(userCommand);
                    parsedCommand.execute(taskList, storage);
                    isExit = parsedCommand.isExit();
                } catch (DukeException e) {
                    Ui.errorMessage(e.getUiMessage());
                }
            }
            scanner.close();
        } catch (DukeException e) {
            Ui.errorMessage(e.getUiMessage());
        }
    }

    public static String sendWelcomeMessage() {
        return Ui.welcomeMessage();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(userInput.getText(), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String userCommand = input;
            Command parsedCommand = CommandParser.parseCommand(userCommand);
            return parsedCommand.execute(taskList, storage);
        } catch (DukeException e) {
            return Ui.errorMessage(e.getUiMessage());
        }
    }
}
