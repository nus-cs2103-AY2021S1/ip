package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a done task.
 */
public class Done extends Task {
    private int i;

    /**
     * Constructs a done task.
     *
     * @param i ith item in the list.
     */
    public Done(int i) {
        super("done", true);
        this.i = i;
    }

    /**
     * Marks the task in the list as done and print the done message.
     * Writes the changes into the file.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markDone(this.i);
        storage.writeData(taskList.getTaskList());
        return ui.showDoneMessage(taskList, this.i);
    }
}
