package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command for Duke to enumerate the entire list of tasks and show it to the user.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:\n");
        taskList.showAllItems();
    }
}
