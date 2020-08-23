package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * Represents the Command to list
 * all tasks in the taskList.
 */
public class ListCommand implements Command {

    /**
     * Prints a representation of the taskList.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<String> tasksListRepr = tasks.getListRepr();
        ui.printWithWrapper(tasksListRepr, true, false);
    }
}
