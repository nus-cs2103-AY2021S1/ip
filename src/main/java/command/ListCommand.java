package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class ListCommand extends Command {
    /**
     * Displays all added tasks for users to view.
     *
     * @throws DukeException If no task has been added yet.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui) throws DukeException {
        if (currList.getNumOfTasks() == 0) {
            // user has not added any task
            throw new DukeException("Nothing has been added to the list yet!");
        } else {
            return currList.displayTasks();
        }
    }
}
