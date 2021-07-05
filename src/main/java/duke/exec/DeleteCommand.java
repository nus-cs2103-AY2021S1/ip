package duke.exec;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Executable {

    // constants
    private static final String DELETED_MESSAGE = "Noted. I've removed this task:";

    private static final String TASK_COUNT_FRONT = "Now you have ";
    private static final String TASK_COUNT_END = " task(s) in the list.";

    // instance variables
    private int index; // zero-based

    // constructor
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * When run, deletes the task at specified index
     *
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     * @return output after running this command
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage store) throws DukeException {
        if (tasks.size() <= index || index < 0) {
            throw DukeException.invalidNumber(); // keep happy path prominent
        }
        Task toDelete = tasks.get(index);
        tasks.remove(index);
        store.write(tasks);
        return ui.outputString(DELETED_MESSAGE, "\t" + toDelete.toString(), TASK_COUNT_FRONT
                + tasks.size() + TASK_COUNT_END);
    }
}
