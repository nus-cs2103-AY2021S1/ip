package alice;

import alice.command.Command;
import alice.command.InvalidCommandException;
import alice.parser.Parser;
import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents the ALICE task manager program.
 */
public class Alice {
    private TaskList tasks;
    private final StorageFile storageFile;
    private final Ui ui;

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
            ui.displayLoadSuccess();
        } catch (AliceException ex) {
            tasks = new TaskList();
            ui.displayLoadError(filePath);
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

    /**
     * Entry point of the program.
     */
    public static void main(String[] args) {
        new Alice("data/tasks.txt").run();
    }
}
