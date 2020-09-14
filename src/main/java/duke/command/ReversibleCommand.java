package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Commands that can be "undoed"
 */
public interface ReversibleCommand extends Command {
    /**
     * Undoes the command.
     * @param taskList  task list to perform actions on.
     * @param ui        ui object to handle IO.
     * @param storage   permanent storage of data.
     * @return  the response of this operation.
     * @throws DukeException    if the task list cannot be saved to the storage.
     */
    String undo (TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
