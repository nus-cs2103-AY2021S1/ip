package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * a class representing commands. it evaluates the commands given by the user and responds appropriately
 */
public abstract class Command {
    protected String fullCommand;
    protected boolean isExit;

    /**
     * class constructor
     * @param fullCommand a string representing the full command given by the user
     */
    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * boolean value representing if the command is an exit command
     * @return true if the command is an exit command and false otherwise
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * performs the appropriate command based on the type of command it is
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
