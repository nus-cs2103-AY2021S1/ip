package duke.commands;

import duke.DukeException;
import duke.Storage;
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
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when no task in list.
     */
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        assert tasklist != null : "Tasklist cannot be null.";
        assert storage != null : "Storage cannot be null.";
        String response = "";
        if (tasklist.getSize() == 0) {
            throw new DukeException("there's nothing on the list yet.");
        } else {
            for (Task i : tasklist.getList()) {
                response += (tasklist.getList().indexOf(i) + 1 + "." + i) + "\n";
            }
        }
        return response;
    }
}
