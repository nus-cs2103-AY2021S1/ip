package dev.jingyen.duke;

import dev.jingyen.duke.model.Task;
import dev.jingyen.duke.parser.InvalidInputException;
import dev.jingyen.duke.parser.InvalidTaskException;
import dev.jingyen.duke.parser.TaskParser;
import dev.jingyen.duke.view.DialogBox;
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

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Duke extends Application {
    private static final String SAVE_FILE_PATH =
            System.getProperty("user.home") + File.separator + ".duke" + File.separator + "tasks.txt";

    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * A Constructor for dev.jingyen.duke.Duke that initializes the save file path to nothing.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("");
        this.tasks = new TaskList();
    }

    /**
     * A Constructor for dev.jingyen.duke.Duke that sets the save file path to a given path.
     *
     * @param filePath the path to the saved tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            this.tasks.addAllTasks(this.storage.load());
        } catch (IOException e) {
            this.ui.showLoadingError();
        }
    }

    /**
     * Initializes an instance of dev.jingyen.duke.Duke, and runs it.
     *
     * @param args The command line args passed to the program
     */
    public static void main(String[] args) {
        new Duke(SAVE_FILE_PATH).run();
    }

    /**
     * Executes an instance of the Duke chatbot. While the user does not input a goodbye command, Duke interprets
     * different commands passed to it and performs different actions, including but not limited to the following:
     * - store a task to be done
     * - mark a task as done
     * - list the tasks to be done
     * - find tasks that match a search term
     * - remove a task from the list of tasks
     */
    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.ui.displayGreeting();

            if (this.storage.hasSavedTasks()) {
                this.ui.displayGreetingReminder(this.tasks.tasksCount());
            }

            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String output = handleCommand(input);

                if (output.equalsIgnoreCase(Ui.GOODBYE_MESSAGE)) {
                    this.storage.saveTasks(this.tasks.getTasks());
                    break;
                }
                System.out.print("> ");
            }

            this.ui.displayGoodbye();
        } catch (IOException e) {
            this.ui.displayMessages(e.getMessage());
        }

    }

    /**
     * Interprets a command that was passed to Duke, and returns a corresponding response based on it. If the command or
     * its arguments were malformed, return an error response to the user.
     *
     * @param input The String containing the command, as well as its arguments
     * @return dev.jingyen.duke.Duke's response to the input string
     */
    private String handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            String command = tokens[0].toLowerCase();
            switch (command) {
            case "list": // show tasks available
                return ui.displayTasks(this.tasks.getTasks());
            case "find":
                String term = input.substring("find".length()).strip();
                List<Task> matchingTasks = this.tasks.searchTasks(term);
                return ui.displayMatchingTasks(matchingTasks);
            case "done": {
                if (tokens.length < 2) {
                    throw new InvalidInputException("Um, you need to tell me what it is you've done.");
                }
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = tasks.getTask(index);
                task.markDone();
                return ui.displayMessages(
                        "Okay. So you've done:",
                        task.toString());
            }
            case "delete":
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = this.tasks.getTask(index);
                this.tasks.deleteTask(index);
                return this.ui.displayMessages(
                        "Right, you no longer want me to track:",
                        task.toString(),
                        this.ui.getTasksLeftMessage(this.tasks.tasksCount()));
            case "todo":
            case "deadline":
            case "event": // it's a new task
                return addTask(command, input);
            case "bye":
                return ui.displayGoodbye();
            default:
                return ui.displayMessages("Um, I don't get what you're saying.");
            }
        } catch (InvalidInputException e) {
            return ui.displayMessages(e.getMessage());
        }
    }

    private String addTask(String command, String input) throws InvalidTaskException {
        Task task = TaskParser.parseInput(command, input);
        this.tasks.addTask(task);
        return this.ui.displayMessages(
                "Okay, you want to:",
                task.toString(),
                this.ui.getTasksLeftMessage(this.tasks.tasksCount()));
    }

    @Override
    public void start(Stage stage) throws Exception {
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
        stage.setTitle("dev.jingyen.duke.Duke");
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }

    /**
     * Returns dev.jingyen.duke.Duke's response String to a user's input.
     */
    public String getResponse(String input) {
        return handleCommand(input);
    }
}
