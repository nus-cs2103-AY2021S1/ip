package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import storage.CommandStorage;

/**
 * A ListCommand object lists all tasks in the list.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-09-01
 */
public class ListCommand extends Command {
    /**
     * Displays all added tasks for users to view.
     *
     * @param inputMsg User's input message to the chat bot.
     * @param currList Current list of tasks.
     * @param ui Ui object relevant to the chat bot.
     * @param commandStorage CommandStorage object to store user commands.
     * @return String message representing tasks in the list.
     * @throws DukeException If no task has been added yet.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui, CommandStorage commandStorage)
            throws DukeException {
        assert currList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";

        if (currList.getNumberOfTasks() > 0) {
            return currList.displayTasks();
        } else {
            // user has not added any task
            throw new DukeException("Nothing has been added to the list yet!");
        }
    }
}
