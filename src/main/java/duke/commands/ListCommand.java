package duke.commands;

import duke.*;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Shows list of tasks of user so far.
 */
public class ListCommand extends Command {

    /**
     * Prints out list of tasks of user.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when no task in list.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.getSize() == 0) {
            throw new DukeException("there's nothing on the list yet.");
        } else {
            for (Task i : tasklist.getList()) {
                ui.showMessage(tasklist.getList().indexOf(i) + 1 + "." + i);
            }
        }
    }
}
