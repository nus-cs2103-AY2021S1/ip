package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Status of chatbot.
     */
    protected boolean isExit = false;
    /**
     * Executes the command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether Duke chatbot is still running.
     * @return Boolean to determine state of Duke.
     */
    public boolean isExit() {
        return isExit;
    };

}
