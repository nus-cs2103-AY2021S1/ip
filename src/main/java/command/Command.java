package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;

public abstract class Command {

    /**
     * Executes the Command.
     *
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will record changes of tasks into the file specified by its path.
     * @throws DukeException Thrown when the command from the user is not feasible.
     * @return The output to be displayed to the user.
     */
    public abstract String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) throws DukeException;

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return True if the program should exit after execution, otherwise false.
     */
    public abstract boolean isExit();
}
