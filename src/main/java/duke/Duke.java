package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Duke is the class encapsulating all application processes.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialises Duke class.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = TaskList.initialiseTaskList(this.storage);
    }

    /**
     * Prints a welcome message to the user.
     * @return A String containing a welcome message to the user.
     */
    public static String sendWelcomeMessage() {
        return DukeMessages.printWelcomeMessage();
    }

    /**
     * Gets a response from Duke given the user input.
     * @param input A string containing user input.
     * @return A string containing the response, or error message if any.
     */
    public String getResponse(String input) {
        try {
            Command parsedCommand = CommandParser.parseCommand(input);
            return parsedCommand.execute(taskList, storage);
        } catch (DukeException e) {
            return DukeMessages.printErrorMessage(e.getUiMessage());
        }
    }
}
