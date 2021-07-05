package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * The find command when user wants to find
 * the tasks in the list.
 */
public class Find extends Task {
    protected String toFind;

    /**
     * Constructs a find task.
     *
     * @param toFind
     */
    public Find(String toFind) {
        super("find", false);
        this.toFind = toFind;
    }

    /**
     * Gets the type of the task.
     *
     * @return String representation of the type.
     */
    @Override
    public String getType() {
        return "find";
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.listMatchedTasks(tasklist, this.toFind);
    }
}
