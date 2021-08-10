package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command by the user to display their list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by displaying the list of tasks.
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If there are no tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";

        if (tasks.getSize() == 0) {
            throw new EmptyListException();
        }

        return ui.showList() + "\n" + tasks.printList();
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
