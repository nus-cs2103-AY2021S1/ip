package alice;

import alice.command.Command;
import alice.command.InvalidCommandException;
import alice.parser.Parser;
import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;
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
import javafx.stage.Window;

/**
 * Runs the ALICE application.
 */
public class Alice extends Application {
    // Properties for GUI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Properties for Alice functionality
    private final TaskList tasks;
    private final StorageFile storageFile;
    private final Ui ui;

    /**
     * Creates an ALICE program.
     * Uses default file path to save ALICE data file.
     * If the file does not exist, creates the appropriate directory and file
     */
    public Alice() {
        ui = new Ui();
        storageFile = new StorageFile();
        try {
            // Read stored data
            tasks = new TaskList(storageFile.load());
            ui.displayInitSuccessMessage();
        } catch (AliceException ex) {
            ui.displayInitFailedMessage();
            // Unable to recover from this exception.
            // Terminate the program.
            throw new RuntimeException(ex);
        }
    }

    /**
     * Creates an ALICE program that reads data from specified location.
     * If the file does not exist, creates the appropriate directory and file
     *
     * @param filePath relative path to the data file.
     */
    public Alice(String filePath) {
        ui = new Ui();
        storageFile = new StorageFile(filePath);
        try {
            // Read stored data
            tasks = new TaskList(storageFile.load());
            ui.displayInitSuccessMessage();
        } catch (AliceException ex) {
            ui.displayInitFailedMessage();
            // Unable to recover from this exception.
            // Terminate the program.
            throw new RuntimeException(ex);
        }
    }

    /**
     * Starts the ALICE program.
     * The method terminates when an exit command is given.
     */
    public void run() {
        ui.displayWelcomeMsg();
        ui.displayLine();
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                String fullCommand = ui.readUserInput();
                ui.displayLine();
                Command c = Parser.parseCommand(fullCommand);
                c.process(tasks, ui, storageFile);
                shouldExit = c.isExitCommand();
            } catch (InvalidCommandException ex) {
                ui.displayWarning(ex.getMessage());
            } catch (AliceException ex) {
                ui.displayError(ex.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public static void main(String[] args) {
        new Alice("data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        // Setting up of required components

        // Container for content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // Bottom bar of chat scroll container.
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Formatting the window to look as expected
        stage.setTitle("Alice");
        stage.setResizable(true);
        stage.setMinWidth(400);
        stage.setMinHeight(600);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325);
        sendButton.setPrefWidth(55);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
