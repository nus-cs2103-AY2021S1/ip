package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;

import duke.Ui;

/**
 * Represent the list task.
 */
public class List extends Task{
    public List() {
        super("list", true);
    }

    /**
     * Print all the tasks in the list.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listTasks(tasklist);
    }
}
