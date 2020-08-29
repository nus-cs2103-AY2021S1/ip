package command;

import exceptions.DukeException;
import task.TaskList;

public abstract class Command {
    protected TaskList tasks;
    protected boolean isExit;

    /**
     * Creates Command object to be executed.
     * @param tasks TaskLists with Tasks to process through.
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
        this.isExit = false;
    }

    /**
     * Executes the command.
     *
     * @throws DukeException
     */
    public void execute() throws DukeException { }
}
