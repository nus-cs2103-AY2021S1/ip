package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

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
     * Prints all the tasks in the list.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.listTasks(taskList);
    }
}
