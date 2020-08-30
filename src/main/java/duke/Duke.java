package duke;

import duke.command.Command;

import duke.exception.DukeException;

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
 * Represents the chat bot itself. Main class.
 */
public class Duke extends Application {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Kenny.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Class constructor.
     *
     * @param filePath A string representing the destination file path.
     */
    public Duke(String filePath) {
        initialiseDuke(filePath);
    }

    /**
     * Class constructor with no specified file path.
     */
    public Duke() {
        initialiseDuke("");
    }

    /**
     * Runs the program. Main method.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Starts the chat bot, continuously takes in user input and executes the relevant command.
     */
    public void run() {
        // Initial greeting, prompt user for commands
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.printBorder(); // Print top border
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printGeneralChatWindow(e.toString());
            } finally {
                ui.printBorder(); // Print bottom border
            }
        }

        ui.printLogo();
    }

    /**
     * Starts the Duke application.
     *
     * @param stage The primary stage that JavaFX provides.
     */
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

        // Print a welcome window and prompt for user input.
        showWelcome();

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
     * Supplementary method to the constructor that creates a duke object.
     *
     * @param filePath A string representing the destination file path.
     */
    private void initialiseDuke(String filePath) {
        ui = new Ui();
        try {
            if (filePath.equals("")) {
                this.storage = new Storage();
            } else {
                this.storage = new Storage(filePath);
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printGeneralChatWindow(e.toString());
            tasks = new TaskList();
        }
    }

    private void showWelcome() {
        Label toShow = new Label(ui.printWelcome());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(toShow, new ImageView(duke))
        );
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
     * Executes a command based on the user input, and returns an appropriate response.
     *
     * @param input A string representing the user input.
     * @return A string representing the response upon the execution of the command.
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

}
