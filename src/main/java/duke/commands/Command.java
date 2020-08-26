package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;

/**
 * The interface of commands.
 */
public interface Command {

    /**
     * The method to be implemented across all Command subclasses.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return A boolean to indicate the terminating command.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     * Ui and Storage.
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
