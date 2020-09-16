package duke;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Duke class that creates a Duke bot to interact with user.
 */

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/meimei.jpeg"));


    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final String filePath;

    /**
     * Constructs a duke that read a file from filePath to retrieve previously stored data
     * @param filePath the file path of the stored data
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui.welcome();
    }

    /**
     * Main method to get duke to start running and ask for user input
     * @return
     */

    public String run(String input) {
        assert !input.isEmpty();
        Parser p = new Parser();

        Command command = p.parse(input);
        try {
            switch (command) {
            case BYE:
                storage.updateDatabase(tasks.getTaskList(), filePath);
                return Ui.bye();
            case TODO:
                input = input.split(" ", 2)[1];
                Task todo = TaskList.createTodo(input);
                return Ui.addedTask(todo, tasks.getSize());
            case EVENT:
                input = input.split(" ", 2)[1];
                Task event = TaskList.createEvent(input);
                return Ui.addedTask(event, tasks.getSize());
            case DEADLINE:
                input = input.split(" ", 2)[1];
                Task deadline = TaskList.createDeadline(input);
                return Ui.addedTask(deadline, tasks.getSize());
            case DELETE:
                input = input.split(" ", 2)[1];
                Task taskToDelete = TaskList.deleteTask(input);
                return Ui.deletedTask(taskToDelete);
            case DONE:
                input = input.split(" ", 2)[1];
                Task doneTask = TaskList.doneTask(input);
                return Ui.doneTask(doneTask);
            case FIND:
                input = input.split(" ", 2)[1];
                List<Task> tasksFound = TaskList.findTask(input);
                return Ui.tasksFound(tasksFound);
            case PRINT_TASKS:
                return Ui.printTaskList(tasks.getTaskList());
            case PRIORITISE:
                input = input.split(" ", 2)[1];
                Task prioritiseTask = TaskList.prioritise(input);
                return Ui.prioritseTask(prioritiseTask);
            case ERROR:
                return Ui.commandError();
            default:
                return input;
            }
        } catch (DukeException e) {
            return Ui.showError(e);
        }
    }

    /**
     * Main method of an entry point of Duke.
     * @param args
     */
    public static void main(String[] args) {
        //new Duke("data").run();
        //new Duke().run();
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setPadding(new Insets(5, 5 , 5 , 5));

        return textToAdd;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
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
        return "Meimei says: " + run(input);
    }
}
