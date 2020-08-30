package duke.tasks;

import duke.Ui;

import duke.tool.Storage;
import duke.tool.TaskList;

/**
 * Represent a done task.
 */
public class Done extends Task {
    private int i;

    /**
     * Constructs a done task.
     * @param i ith item in the list.
     */
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
