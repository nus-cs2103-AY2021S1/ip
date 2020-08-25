package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;

import duke.Ui;

/**
 * Represent a delete task.
 */
public class Delete extends Task{
    int i;
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
     */
    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        Task removed = tasklist.delete(i);
        ui.showDeleteMessage(tasklist, removed);
        storage.writeData(tasklist.taskList);
    }
}
