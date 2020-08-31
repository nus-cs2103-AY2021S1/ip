package duke.tasks;

import duke.ui.Ui;
import duke.tool.Storage;
import duke.tool.TaskList;

/**
 * Represent a delete task.
 */
public class Delete extends Task {
    private int i;

    /**
     * Constructs a Delete task.
     * @param i ith item in the list.
     */
    public Delete(int i) {
        super("delete", true);
        this.i = i;
    }

    /**
     * Delete the certain task in the list and print the deleted message.
     * Write the changes into the file.
     * @param tasklist
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        Task removed = tasklist.delete(i);
        storage.writeData(tasklist.getTaskList());
        return ui.showDeleteMessage(tasklist, removed);
    }
}
