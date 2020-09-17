package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.data.TaskList;
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
     * Sets up the required objects, TaskList, Storage and UI
     * and gets the task list from the storage.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.taskList = storage.load();
        } catch (Storage.StorageOperationException | FileNotFoundException e) {
            System.out.println("An error occurred while loading the task list. Please rerun the program again.");
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
            return new CommandResult(e.getMessage());
        }
    }

}
