package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;

public abstract class Command {

    /**
     * Executes the Command.
     *
     * @param tasks The TaskList manipulated by the Command.
     * @param storage The Storage which will record changes of tasks into the file specified by its path.
     * @throws DukeException Thrown when the command from the user is not feasible.
     * @return The output to be displayed to the user.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return True if the program should exit after execution, otherwise false.
     */
    public abstract boolean isExit();
}
