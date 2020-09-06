package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * This Command will cause Duke to list all the Tasks inside the TaskList.
 */
public class ListCommand implements Command {
    @Override
    public void execute(Ui ui, TaskList list) {
        if (list.size() == 0) {
            ui.say("There are no items in your list.");
        } else {
            ui.say("Here are the tasks in your list:\n" + Helper.tasksToDisplayListString(list));
        }
    }
}
