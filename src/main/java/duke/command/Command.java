package duke.command;

import duke.exception.InvalidCommandException;
import duke.main.TaskList;

/**
 * An abstraction for a command parsed into Duke.
 * It supports its respective operations on the TaskList.
 */
public abstract class Command {
    protected final TaskList taskList;

    Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Performs the operation on the TaskList, then prints the response.
     * The response can either be success or a failure message.
     *
     * @return Message detailing the outcome of the command.
     * @throws InvalidCommandException If invalid syntax is given in the command.
     */
    public abstract String execute() throws InvalidCommandException;
}
