package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an action corresponding to a user input.
 */
public interface Command {
    /**
     * Performs the action to be taken.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True if Duke should continue running.
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage);
}