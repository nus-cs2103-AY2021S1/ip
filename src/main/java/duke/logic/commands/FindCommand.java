package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.exceptions.NoTasksFoundException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command by the user to find matching tasks with a keyword. A FindCommand object has a
 * keyword variable, which is the keyword used by the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a FindCommand object.
     *
     * @param keyword The keyword to search for matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding the matching tasks using the keyword and displaying
     * it in a list.
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If there are no matching tasks with the specified keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";

        TaskList foundTasks = tasks.findTasks(keyword);

        if (foundTasks.getSize() == 0) {
            throw new NoTasksFoundException();
        }

        return ui.showFind() + "\n" + foundTasks.printList();
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
