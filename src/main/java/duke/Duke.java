package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.data.TaskList;
import duke.data.exception.IllegalValueException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a {@code Duke} object.
     */
    public Duke() {
        startDuke();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program until termination.  */
    public void run() {
        runCommandLoopUntilExitCommand();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     */
    public String startDuke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.taskList = storage.load();
            return ui.getWelcomeMessage();
        } catch (Storage.StorageOperationException | FileNotFoundException | IllegalValueException e) {
            return ui.getInitFailedMessage();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @return result of the user input
     */
    public String getResponse(CommandResult result) {
        return ui.getResultToUser(result);
    }

    /**
     * Executes the command and returns the result.
     *
     * @param userInput user input
     * @return result of the user input
     */
    public CommandResult executeCommand(String userInput) {
        try {
            Command command = new Parser().parseCommand(userInput);
            command.setData(taskList);
            CommandResult result = command.execute();
            storage.save(taskList);
            return result;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        try {
            Command command;
            do {
                String userInput = ui.getUserCommand();
                command = new Parser().parseCommand(userInput);
            } while (!ExitCommand.isExit(command));
            storage.save(taskList);
        } catch (Storage.StorageOperationException e) {
            e.printStackTrace();
        }
    }

}
