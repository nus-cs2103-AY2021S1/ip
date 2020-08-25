package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;

import duke.Ui;

/**
 * Represent a done task.
 */
public class Done extends Task{
    int i;
    public Done(int i) {
        super("done", true);
        this.i = i;
    }

    /**
     * Mark the task in the list as done and print the done message.
     * Write the changes into the file.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.markDone(this.i);
        ui.showDoneMessage(tasklist, this.i);
        storage.writeData(tasklist.taskList);
    }
}
