package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The list command helps to print all the tasks in the task list
 * in a neatly-formatted way.
 */
public class ListCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage(tasks.getPrintMessage());
    }
}
