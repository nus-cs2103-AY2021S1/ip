package duke.tasks;

import duke.Ui;
import duke.tool.Storage;
import duke.tool.TaskList;

/**
 * The find command when user wants to find
 * the tasks in the list.
 */
public class Find extends Task {
    protected String toFind;

    /**
     * Constructs a find task.
     * @param toFind
     */
    public Find(String toFind) {
        super("find", false);
        this.toFind = toFind;
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listMatchedTasks(tasklist, this.toFind);
    }
}
