package juke.command;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to display an error message to User.
 */
public class ErrorCommand extends Command {

    private String message;

    /**
     * Constructs an ErrorCommand with the given message.
     * @param message The error message to be output to user.
     */
    public ErrorCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * Executes the output of the error message to the user.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return Response Error Message to be output by chatbot.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return this.message;
    }
}

