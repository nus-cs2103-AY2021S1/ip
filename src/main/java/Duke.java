import java.io.FileNotFoundException;

import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;
import data.TaskList;
import data.exception.IllegalValueException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run(args);
    }

    /** Runs the program until termination.  */
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param args arguments supplied by the user at program launch
     */
    private void start(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = initializeStorage(args);
            this.taskList = storage.load();
            ui.showWelcomeMessage();
        } catch (Storage.StorageOperationException | FileNotFoundException | IllegalValueException e) {
            ui.showInitFailedMessage();
        }
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        try {
            ui.showGoodbyeMessage();
            storage.save(taskList);
            System.exit(0);
        } catch (Storage.StorageOperationException e) {
            ui.showToUser(e.getMessage());
        }
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList);
            CommandResult result = command.execute();
            storage.save(taskList);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates the Storage object based on the user specified path (if any) or the default storage path.
     * @param args arguments supplied by the user at program launch
     * @throws Storage.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private Storage initializeStorage(String[] args) throws Storage.InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = args.length > 0;
        return isStorageFileSpecifiedByUser ? new Storage(args[0]) : new Storage();
    }

}
