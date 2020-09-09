import java.io.IOException;
import java.util.Scanner;

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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is a chatbot that allows users to send input to perform tasks
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    // GUI variables
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    // Pre-set images
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    /**
     * Creates a new Duke chatbot.
     *
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            // Load task list from saved file
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            // Create task list
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Creates a new Duke chatbot and load tasks from storage.
     *
     * @param filePath path to the storage file to load tasks from
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        assert !filePath.isBlank();
        storage = new Storage(filePath);
        try {
            // Load task list from saved file
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            // Create task list
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public String reply(String userInput) {
        assert !userInput.isBlank();
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        assert !userCommand.isBlank();

        // Validate command
        try {
            Parser.validateCommand(userCommand);
        } catch (DukeException e) {
            return "Sorry, that looks like an invalid command! " + e.getMessage();
        }

        switch (userCommand) {
            // Exit the program
            case "bye":
                return "Bye! Hope to see you again soon!";

            // List the tasks available in taskList
            case "list":
                return taskList.listTasks();

            case "find":
                try {
                    return taskList.searchKeyword(Parser.parseFind(userInput));
                } catch (DukeException e) {
                    return "Sorry, I can't perform find for you! " + e.getMessage();
                }

            // Create a to-do task
            case "todo":
                try {
                    String message = taskList.addTask(Parser.parseTodo(userInput));
                    storage.save(taskList);
                    return message;
                } catch (DukeException | IOException e) {
                    return "Sorry, I can't add that todo! " + e.getMessage();
                }

            // Create a deadline task (contains "/by")
            case "deadline":
                try {
                    String message = taskList.addTask(Parser.parseDeadline(userInput));
                    storage.save(taskList);
                    return message;
                } catch (DukeException | IOException e) {
                    return "Sorry, I can't add that deadline! " + e.getMessage();
                }

            // Create a event task (contains "/at")
            case "event":
                try {
                    String message = taskList.addTask(Parser.parseEvent(userInput));
                    storage.save(taskList);
                    return message;
                } catch (DukeException | IOException e) {
                    return "Sorry, I can't add that event! " + e.getMessage();
                }

            // Mark the identified task as done
            case "done":
                try {
                    String message = taskList.setDone(Parser.parseDone(userInput));
                    storage.save(taskList);
                    return message;
                } catch (DukeException | IOException e) {
                    return "Sorry, I can't mark that as done! " + e.getMessage();
                }

            // Delete a task
            case "delete":
                try {
                    String message = taskList.deleteTask(Parser.parseDelete(userInput));
                    storage.save(taskList);
                    return message;
                } catch (DukeException | IOException e) {
                    return "Sorry, I can't delete that task! " + e.getMessage();
                }

            default:
                return "";
        }
    }

    /**
     * Runs main conversation loop with Duke chatbot.
     */
    public void run() {
        // Introduction messages
        System.out.println("Hello! I'm Duke! I'm a chatbot-based To-Do list manager.");
        System.out.println("My available commands are: todo, deadline, event, done, list, delete, bye");
        System.out.println("What can I do for you today? :)");

        // Main conversation loop
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println(reply(sc.nextLine()));
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Formatting the window to look as expected
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Gets the response from Duke.
     * @param input Input from the user
     * @return the reply from Duke
     */
    private String getResponse(String input) {
        return reply(input);
    }
}
