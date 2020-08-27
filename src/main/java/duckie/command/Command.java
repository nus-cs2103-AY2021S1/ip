package duckie.command;

import duckie.task.TaskList;
import duckie.Ui;
import duckie.Storage;
import duckie.exception.DuckieException;

/**
 * Parent class of all the Commands action
 */
public abstract class Command {

    /**
     * Execute the command according to the input given
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     * @throws DuckieException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException;

    /**
     * Check if the Command signifies exit of the chatbot
     * @return
     */
    public boolean isExit() {
        return false;
    }

}
