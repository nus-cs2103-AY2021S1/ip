import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import java.io.FileNotFoundException;

import util.*;

/**
 * Duke's main class.
 *
 * Consists of a Storage, TaskList and Ui.
 * Contains the program loop in run() method.
 */
public class Duke{
    // Duke's program variables
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //Duke's GUI variables
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //Duke's images
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Duke's Default Constructor.
     */
    public Duke() {

    }

    /**
     * Duke's Constructor.
     * Initializes ui, storage and tasks.
     * Loads in save file (if any).
     *
     * @param filePath  File path for save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
            System.out.println("... Oh! You're back!\nEr, gimme a sec...");
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke within a program loop.
     *
     * 1. Displays Greetings
     * 2. Takes in user input.
     * 3. Parse user input.
     * 4. Respond to command in user input.
     * 5. Return to 2 and loop
     * OR save and exit from program.
     */
    public void run() {
        // Greet first
        ui.displayGreetings();

        boolean isExit = false;
        // Loop for program
        while (!isExit) {
            try {
                // Read user input
                String userInput = ui.readUserInput();
                // Parse out the command from the user input
                Command cmd = Parser.parse(userInput);
                // Use switch to process the commands
                switch (cmd) {
                case EXIT:
                    isExit = true;
                    break;
                case LIST:
                    ui.printList(tasks);
                    break;
                case TODO:
                    ui.printOutputSymbol();
                    tasks.createTodo(userInput);
                    break;
                case DEADLINE:
                    ui.printOutputSymbol();
                    tasks.createDeadline(userInput);
                    break;
                case EVENT:
                    ui.printOutputSymbol();
                    tasks.createEvent(userInput);
                    break;
                case INVALID:
                    // Duke does not recognize the commands
                    ui.printOutputSymbol();
                    throw new DukeException("Sorry, I don't recognize what you just entered...");
                case DONE:
                    ui.printOutputSymbol();
                    tasks.markTaskDone(userInput);
                    break;
                case DELETE:
                    ui.printOutputSymbol();
                    tasks.deleteTask(userInput);
                    break;
                case FIND:
                    ui.printOutputSymbol();
                    tasks.searchForKeyword(userInput);
                    break;
                default:
                    throw new DukeException("Sorry I think I something went wrong...");
                }
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                // Always end off with a line breaker
                ui.printLineBreaker();
            }
        }

        // Display farewells
        ui.displayFarewells();

        // Try to Save the data
        try {
            storage.saveToFile(tasks.getTasks());
        } catch (DukeException e) {
            ui.printError(e);
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Makes Duke's GUI.
     *
     * @param stage primary stage provided by JavaFX.
     */
    /**
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

        AnchorPane.setBottomAnchor(sendButton, 2.0);
        AnchorPane.setRightAnchor(sendButton, 2.0);

        AnchorPane.setLeftAnchor(userInput , 2.0);
        AnchorPane.setBottomAnchor(userInput, 2.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    */

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /**
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    } */

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
