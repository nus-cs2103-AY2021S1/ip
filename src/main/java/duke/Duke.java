package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DateParseException;
import duke.exception.DukeException;
import duke.exception.StorageException;
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
     * @throws DateParseException if Task created from file information cannot be stored in local storage.
     * @throws StorageException if Task date (if any) cannot be parsed into LocalDate object.
     */
    public Duke() throws DateParseException, StorageException {
        this.storage = new Storage();
        this.taskList = TaskList.initialiseTaskList(this.storage);
    }

    /**
     * Prints the welcome message.
     * @return A string containing the welcome message to be printed.
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
