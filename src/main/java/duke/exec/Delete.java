package duke.exec;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Delete implements Executable {

    // constants
    private static final String DELETED_MESSAGE = "Noted. I've removed this task:";

    private static final String TASK_COUNT_FRONT = "Now you have ";
    private static final String TASK_COUNT_END = " task(s) in the list.";

    // instance variables
    private int index; // zero-based

    // constructor
    public Delete(int index) {
        this.index = index;
    }

    /**
     * When run, deletes the task at specified index
     *
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage store) {
        Task toDelete = tasks.get(index);
        tasks.remove(index);
        ui.display(DELETED_MESSAGE, "\t" + toDelete.toString(), TASK_COUNT_FRONT
                + tasks.size() + TASK_COUNT_END);
        store.write(tasks);
    }
}
