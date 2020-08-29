package alice;

import alice.command.Command;
import alice.command.InvalidCommandException;
import alice.parser.Parser;
import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Runs the ALICE application.
 */
public class Alice extends Application {
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

    @Override
    public void start(Stage primaryStage) {
        // Create new Label control
        Label helloWorld = new Label("Hello World!");
        // Setting the scene to be our Label
        Scene primaryScene = new Scene(helloWorld);

        // Setting the stage to show our screen
        primaryStage.setScene(primaryScene);
        // Render the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        new Alice("data/tasks.txt").run();
    }
}
