package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * The interface of commands.
 */
public interface Command {

    /**
     * The method to be implemented across all Command subclasses.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The response based on the command.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     *                       Ui and Storage.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
