import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duke is a Personal Assistant bot that helps you to keep track of various tasks.
 */
public class Duke extends Application {
    private static final String DEFAULT_FILE_NAME = "default_tasks.txt";
    private TaskList tasks;
    private Storage storage;
    private Path saveFolder = Paths.get(System.getProperty("user.dir"), "data");
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a duke application
     */
    public Duke() {
        ui = new Ui();
        this.storage = new Storage(saveFolder.resolve(DEFAULT_FILE_NAME));
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException dukeException) {
            ui.show(ui.getExceptionMessage(dukeException));
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Starts duke
     */
    public void run() {
        ui.showWelcome();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String command = ui.readCommand();
                String[] commandBlocks = Parser.parse(command);
                ui.showLine();
                String response = handleCommand(commandBlocks);
                ui.show(response);
                isEnd = response.equals(ui.getByeMessage());
            } catch (DukeException dukeException) {
                ui.show(ui.getExceptionMessage(dukeException));
            } finally {
                ui.showLine();
            }
        }
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        Label dukeText = new Label(ui.getWelcomeMessage());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
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
        if (dukeText.getText().equals(ui.getByeMessage())) {
            Platform.exit();
        }
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            String[] commandBlocks = Parser.parse(input);
            return handleCommand(commandBlocks);
        } catch (DukeException dukeException) {
            return ui.getExceptionMessage(dukeException);
        }
    }



    private String handleCommand(String[] commands) throws DukeException {
        Task task;
        switch (commands[0]) {
        case "todo":
            Parser.checkValidAddCommand(commands);
            task = tasks.addTask(new Todo(commands[1]));
            return ui.getAddTaskMessage(task, tasks);
        case "deadline":
            Parser.checkValidAddCommand(commands);
            task = tasks.addTask(Deadline.create(commands[1]));
            return ui.getAddTaskMessage(task, tasks);
        case "event":
            Parser.checkValidAddCommand(commands);
            task = tasks.addTask(Event.create(commands[1]));
            return ui.getAddTaskMessage(task, tasks);
        case "list":
            return ui.getTaskList(tasks);
        case "done":
            task = tasks.markTaskDone(commands);
            return ui.getTaskDoneMessage(task);
        case "delete":
            task = tasks.deleteTask(commands);
            return ui.getDeleteMessage(task, tasks);
        case "save":
            storage.save(tasks);
            return ui.getSaveMessage(tasks, storage);
        case "bye":
            storage.save(tasks);
            return ui.getByeMessage();
        case "find":
            String result = tasks.getSearchResult(commands);
            return ui.getSearchResults(result);
        case "load":
            Parser.checkValidSaveFile(commands);
            Storage newStorage = new Storage(saveFolder.resolve(commands[1] + ".txt"));
            List<Task> newTasks = newStorage.load();
            this.tasks = new TaskList(newTasks);
            this.storage = newStorage;
            return ui.getChangeSavePathMessage(tasks, storage);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means");
        }
    }


}
