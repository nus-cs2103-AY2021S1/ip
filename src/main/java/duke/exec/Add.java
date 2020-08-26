package duke.exec;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Add implements Executable{

    // constants
    private static final String TASK_COUNT_FRONT = "Now you have ";
    private static final String TASK_COUNT_END = " task(s) in the list.";

    private static final String ADDED_MESSAGE = "Got it. I've added this task:";

    // instance variables
    Task toAdd;

    // constructor
    public Add(Task task) {
        this.toAdd = task;
    }

    /**
     * When run, adds a task to the given list of tasks
     *
     * @param tasks the list of tasks to append to
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage store) {
        tasks.add(toAdd);
        ui.display(ADDED_MESSAGE, "\t" + toAdd.toString(), TASK_COUNT_FRONT
                + tasks.size() + TASK_COUNT_END);
        store.write(tasks);
    }
}
