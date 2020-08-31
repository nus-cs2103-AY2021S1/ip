package duke.command;

import duke.Storage;
import duke.task.NumberedTask;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

import java.util.List;

/**
 * Command for user to view all Tasks created. Created by using "list"
 */
public class ListCommand extends Command {

    /**
     * Gets all Tasks stored in TaskList and format a String to display all the Tasks to the user.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @return Response object containing the formatted String to be displayed to the user by the GUI
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) {
        List<NumberedTask> numberedTasks = tasks.tasksToString();
        return new Response(false, ui.allTasksToString(numberedTasks));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}
