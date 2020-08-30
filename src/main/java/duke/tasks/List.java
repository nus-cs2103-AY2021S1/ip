package duke.tasks;

import duke.Ui;
import duke.tool.Storage;
import duke.tool.TaskList;

/**
 * Represent the list task.
 */
public class List extends Task {
    /**
     * Constructs a list command.
     */
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
