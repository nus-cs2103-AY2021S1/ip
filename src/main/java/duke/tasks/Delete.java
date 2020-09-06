package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete task.
 */
public class Delete extends Task {
    private int i;

    /**
     * Constructs a Delete task.
     *
     * @param i ith item in the list.
     */
    public Delete(int i) {
        super("delete", true);
        this.i = i;
    }

    /**
     * Deletes the certain task in the list and print the deleted message.
     * Writes the changes into the file.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task removed = taskList.delete(i);
        storage.writeData(taskList.getTaskList());
        return ui.showDeleteMessage(taskList, removed);
    }
}
