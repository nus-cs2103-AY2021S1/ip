package command;

import exceptions.DukeException;
import task.TaskList;

public abstract class Command {
    protected TaskList tasks;

    /**
     * Creates Command object to be executed.
     */
    public Command() {
        this.tasks = null;
    }

    /**
     * Creates Command object to be executed.
     * @param tasks TaskLists with Tasks to process through.
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     * @return String to be printed, if any.
     * @throws DukeException
     */
    public String execute() throws DukeException {
        return null;
    }
}
