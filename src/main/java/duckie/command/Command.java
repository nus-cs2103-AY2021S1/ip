package duckie.command;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.task.TaskList;
import duckie.ui.Ui;

/**
 * Parent class of all the Commands action.
 */
public abstract class Command {

    /**
     * Execute the command according to the input given.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui to interact with the users.
     * @param storage Storage to write to File.
     * @return String Returns the required message.
     * @throws DuckieException Throw DuckieException caught to the later method.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException;

    /**
     * Check if the Command signifies exit of the chatbot.
     *
     * @return False to signify platform exit.
     */
    public boolean isExit() {
        return false;
    }

}
