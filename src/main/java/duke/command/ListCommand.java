package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Command for user to view all Tasks created. Created by using "list"
 */
public class ListCommand extends Command {

    /**
     * Print out each Task sequentially in TaskList.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksToString = tasks.tasksToString();
        System.out.println(tasksToString);
        return new Response(false, ui.formatMessage(tasksToString));
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
