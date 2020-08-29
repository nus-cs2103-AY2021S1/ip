import exceptions.DukeException;
import exceptions.DukeUnknownCommandException;

import java.io.IOException;

import java.time.format.DateTimeParseException;

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


/**
 * Duke Class is the main class that will run based on different commands
 * given by user. Available commands include todo, deadline, event,
 * done, delete. Todo, deadline and event are different types of tasks command.
 * Done and delete are commands to mark the list item as done or to
 * delete it respectively.
 * Todo, deadline and event are followed by a description or message.
 * Eg: todo do CS2103T project
 * This would translate to a todo list item added into the user's overall list.
 * Event and Deadline would require /at and /by to specify the timing.
 * Description of done and delete would be a number to specify which
 * item in the list that should be marked as done or deleted.
 */
public class Duke extends Application {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() { }

    /**
     * Constructor for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
    }

    /**
     * Invoke run for duke chatbot programme.
     *
     * @param args argument.
     * @throws IOException if file does not exist.
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Run Duke programme depending on the different commands
     * given by user.
     *
     * @throws IOException if file does not exist.
     */
    public void run() throws IOException {
        storage.handleLoad();
        ui.greeting();
        ui.showList();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = new Command(taskList, ui);
                String toEcho = ui.getCommand();
                if (toEcho.equals("bye")) {
                    ui.bye();
                    isExit = true;
                } else if (toEcho.equals("list")) {
                    ui.showList();
                } else if (toEcho.startsWith("done")) {
                    c.handleDone(toEcho);
                } else if (toEcho.startsWith("todo")) {
                    c.handleTodo(toEcho);
                } else if (toEcho.startsWith("deadline")) {
                    c.handleDeadline(toEcho);
                } else if (toEcho.startsWith("event")) {
                    c.handleEvent(toEcho);
                } else if (toEcho.startsWith("delete")) {
                    c.handleDelete(toEcho);
                } else if (toEcho.startsWith("find")) {
                    c.handleFind(toEcho);
                } else {
                    throw new DukeUnknownCommandException();
                }
                storage.saveTasks();
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseError(e.getMessage());
            } catch (DukeException e) {
                ui.printError(e.getMessage());
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}
